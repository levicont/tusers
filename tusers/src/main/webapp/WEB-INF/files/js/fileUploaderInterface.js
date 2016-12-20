$(document).ready(function() {


    // Консоль
    //var console = $("#console");

    // Инфа о выбранных файлах
    var countInfo = $("#file-count");
    var sizeInfo = $("#file-size");

    // Стандарный input для файлов
    var fileInput = $('#file-field');
    
    // ul-список, содержащий миниатюрки выбранных файлов
    var imgList = $('#file-list-container');

    //
    var mainImage = $('#main-image');
    
    var errorField = $('#upload-error');
    // Контейнер, куда можно помещать файлы методом drag and drop
    //var dropBox = $('#img-container');

    // Счетчик всех выбранных файлов и их размера
    var imgCount = 0;
    var imgSize = 0;
    
    
    ////////////////////////////////////////////////////////////////////////////


    // Вывод в консоль
    /*function log(str) {
        $('<p/>').html(str).prependTo(console);
    }
    */

    function logError(str){
        $('<i/>').addClass('fa').addClass('fa-times').appendTo(errorField);
        errorField.text(str);
    }

    // Вывод инфы о выбранных
    function updateInfo() {
        countInfo.text(imgCount);
        sizeInfo.text(Math.round(imgSize / 1024)+"Kb");
    }

    // Обновление progress bar'а
    function updateProgress(bar, value) {
        var width = bar.attr('aria-valuemax');       
        bar.attr('aria-valuenow', value).attr('style', 'width:'+value+'%');
    }



    // Отображение выбраных файлов и создание миниатюр
    function displayFiles(files) {
        var imageType = /image.*/;
        var num = 0;
        
        $.each(files, function(i, file) {
            
            // Отсеиваем не картинки
            if (!file.type.match(imageType)) {               
                return true;
            }
            
            num++;
            
            // Создаем элемент li и помещаем в него название, миниатюру и progress bar,
            // а также создаем ему свойство file, куда помещаем объект File (при загрузке понадобится)
            var row = $('<div/>').appendTo(imgList);
            row.addClass('row').addClass('img-item');

            var colImg = $('<div/>').appendTo(row);
            colImg.addClass('col-lg-2');
            var img = $('<img/>').appendTo(colImg);
            img.addClass('lvg-img-icon');
            
            var colImgName = $('<div/>').appendTo(row);
            colImgName.addClass('col-lg-6');
            var imgName = $('<p/>').appendTo(colImgName);
            imgName.addClass('lvg-img-name').text(file.name);

            var colImgProgress = $('<div/>').appendTo(row);
            colImgProgress.addClass('col-lg-4');
            var imgProgress = $('<div/>').appendTo(colImgProgress);
            imgProgress.addClass('progress');
            var progressBar = $('<div/>').addClass('progress-bar').addClass('progress-bar-success').appendTo(imgProgress);
            progressBar.attr('role','progress').attr('aria-valuenow','0').attr('aria-valuemin','0').attr('aria-valuemax','100');
            progressBar.css('width','0%');

            row.get(0).file = file;
           

            // Создаем объект FileReader и по завершении чтения файла, отображаем миниатюру и обновляем
            // инфу обо всех файлах
            var reader = new FileReader();
            reader.onload = (function(aImg) {
                return function(e) {
                    aImg.attr('src', e.target.result);
                    aImg.attr('width', 80);                   
                    imgCount++;
                    imgSize += file.size;
                    updateInfo();
                };
            })(img);
            
            reader.readAsDataURL(file);
        });
    }
    
    
    ////////////////////////////////////////////////////////////////////////////


    // Обработка события выбора файлов через стандартный input
    // (при вызове обработчика в свойстве files элемента input содержится объект FileList,
    //  содержащий выбранные файлы)
    fileInput.bind({
        change: function() {          
            displayFiles(this.files);
        }
    });
          

    // Обработка событий drag and drop при перетаскивании файлов на элемент dropBox
    // (когда файлы бросят на принимающий элемент событию drop передается объект Event,
    //  который содержит информацию о файлах в свойстве dataTransfer.files. В jQuery "оригинал"
    //  объекта-события передается в св-ве originalEvent)
    /*dropBox.bind({
        dragenter: function() {
            $(this).addClass('highlighted');
            return false;
        },
        dragover: function() {
            return false;
        },
        dragleave: function() {
            $(this).removeClass('highlighted');
            return false;
        },
        drop: function(e) {
            var dt = e.originalEvent.dataTransfer;
            log(dt.files.length+" файл(ов) выбрано через drag'n'drop");
            displayFiles(dt.files);
            return false;
        }
    });
    */

    // Обаботка события нажатия на кнопку "Загрузить". Проходим по всем миниатюрам из списка,
    // читаем у каждой свойство file (добавленное при создании) и начинаем загрузку, создавая
    // экземпляры объекта uploaderObject. По мере загрузки, обновляем показания progress bar,
    // через обработчик onprogress, по завершении выводим информацию
    $("#upload-all").click(function() {
        var csrfParamName = $('#_csrf').attr('name');
        var csrfParamValue = $('#_csrf').attr('value');


        imgList.find('.img-item').each(function() {

            var uploadItem = this;
            var pBar = $(uploadItem).find('.progress-bar');           
            new uploaderObject({
                file:       uploadItem.file,
                url:        './upload',
                fieldName:  'my-pic',
                csrfName:   csrfParamName,
                csrfValue:   csrfParamValue,
                onprogress: function(percents) {
                    updateProgress(pBar, percents);
                },                
                oncomplete: function(done, data) {
                    if(done) {      
                       
                        updateProgress(pBar, 100);
                        mainImage.attr('alt', data.responseText);
                        var tmpReader = new FileReader();
                        tmpReader.readAsDataURL(uploadItem.file);
                        tmpReader.onload = (function(aImg) {
                            return function(e) {
                                mainImage.attr('src', e.target.result);                                
                            };
                        })(mainImage);
                       
                       
                        var currentProgressBar = $(uploadItem).find('.progress');
                        $(uploadItem).fadeOut(2000);
                        $(uploadItem).find('.lvg-img-icon').remove();
                        $(uploadItem).removeClass('img-item');
                        if( $('.img-item').length < 1){                         
                            $(sizeInfo).text(0);
                            $(countInfo).text(0);                          
                        }
                       
                    } else {
                        logError('Error during uploading file "'+uploadItem.file.name+'": </br>'+this.lastError.text);
                    }
                }
            });
        });
    });

    
    // Проверка поддержки File API в браузере
    if(window.FileReader == null) {
        logError('Your web browser not support File API!');
    }
    
    
});
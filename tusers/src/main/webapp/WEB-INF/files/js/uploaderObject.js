/*
 * Объект-загрузчик файла на сервер.
 * Передаваемые параметры:
 * file       - объект File (обязателен)
 * url        - строка, указывает куда загружать (обязателен)
 * fieldName  - имя поля, содержащего файл (как если задать атрибут name тегу input)
 * onprogress - функция обратного вызова, вызывается при обновлении данных
 *              о процессе загрузки, принимает один параметр: состояние загрузки (в процентах)
 * oncopmlete - функция обратного вызова, вызывается при завершении загрузки, принимает два параметра:
 *              uploaded - содержит true, в случае успеха и false, если возникли какие-либо ошибки;
 *              data - в случае успеха в него передается ответ сервера
 *              
 *              если в процессе загрузки возникли ошибки, то в свойство lastError объекта помещается
 *              объект ошибки, содержащий два поля: code и text
 */

var uploaderObject = function(params) {   
    if(!params.file || !params.url) {
        return false;
    }

    this.xhr = new XMLHttpRequest();
    this.reader = new FileReader();

    this.progress = 0;
    this.uploaded = false;
    this.successful = false;
    this.lastError = false;
    
    var self = this;    

    self.reader.onload = function() {        
        self.xhr.upload.addEventListener("progress", function(e) {
            if (e.lengthComputable) {
                self.progress = (e.loaded * 100) / e.total;
                var isCalled = false;
                if(params.onprogress instanceof Function) {
                    params.onprogress.call(self, Math.round(self.progress));                   
                }
            }
        }, false);

        self.xhr.upload.addEventListener("load", function(){
            self.progress = 100;
            self.uploaded = true;
        }, false);

        self.xhr.upload.addEventListener("error", function(){            
            self.lastError = {
                code: 1,
                text: 'Error uploading on server'
            };
        }, false);

        self.xhr.onreadystatechange = function () {              
            var callbackDefined = params.oncomplete instanceof Function;
            if (this.readyState == 4) {

                if(this.status == 200) {                    
                    if(!self.uploaded) {
                        if(callbackDefined) {
                            params.oncomplete.call(self, false);
                        }
                    } else {                       
                        self.successful = true;
                        if(callbackDefined) {
                            params.oncomplete.call(self, true, this);
                        }
                    }
                } else if(this.status == 403){
                        var url="/tusers"
                        $(location).attr("href", url)
                } else {
                    self.lastError = {
                        code: this.status,
                        text: 'HTTP response code is not OK ('+this.status+')'
                    };
                    if(callbackDefined) {
                        params.oncomplete.call(self, false);
                    }
                }
            }
        };

       
        var formData = new FormData();
        formData.append("fileName",params.file.name);
        formData.append("file", params.file);     
        self.xhr.open("POST", params.url);
        self.xhr.setRequestHeader("X-CSRF-TOKEN", params.csrfValue);

                
        if(self.xhr.sendAsBinary) {
            // firefox         
            self.xhr.sendAsBinary(formData);
        } else {
            // chrome (W3C spec.) 
            self.xhr.send(formData);
           
        }

    };  
    self.reader.readAsBinaryString(params.file);   
    
};
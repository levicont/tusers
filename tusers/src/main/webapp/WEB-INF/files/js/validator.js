/**
 * 
 */
;
function sbmUser() {
	var name = $('#name').val();
	var surname = $('#surname').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var repassword = $('#repassword').val();
	var hasError = false;

	if (!isValidName(name)) {
		printError($('#name-lvg-error'), "Name is not valid!");
		hasError = true;
	} else
		print($('#name-lvg-error'), "OK");

	if (!isValidSurname(surname)) {
		printError($('#surname-lvg-error'), "Surname is not valid!");
		hasError = true;
	} else
		print($('#surname-lvg-error'), "OK");

	if (!isValidEmail(email)) {
		printError($('#email-lvg-error'), "Email is not valid!");
		hasError = true;
	} else
		print($('#email-lvg-error'), "OK");

	if (!isValidPassword(password)) {
		printError($('#password-lvg-error'), "Password is not valid!");
		hasError = true;
	} else
		print($('#password-lvg-error'), "OK");

	if (!isValidRePassword(password, repassword)) {
		printError($('#repassword-lvg-error'), "Passwords do not match");
		hasError = true;
	} else
		print($('#repassword-lvg-error'), "OK");
	
	if(hasError){			
		
	}
	else{
		$('#registerForm').attr('action', 'registration');
		$('#registerForm').attr('method', 'post');
		$('#registerForm').attr('modelAttribute', 'user');
		$('#registerForm').submit();
		
	}

}

function isValidName(Name) {
	var exp = /^[a-zA-Z\u0410-\u044F]{1,20}$/;
	if (Name.search(exp) > -1)
		return true;
	else
		return false;
}

function isValidEmail(Email) {
	var exp = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
	if (Email.search(exp) > -1)
		return true;
	else
		return false;
}

function isValidPassword(Password) {
	var exp = /^[a-zA-Z0-9!@%&?]{2,12}$/;
	if (Password.search(exp) > -1)
		return true;
	else
		return false;
}

function isValidRePassword(Password, Repassword) {
	if (Password === Repassword)
		return true;
	else
		return false;
}

function isValidSurname(Surname) {
	return isValidName(Surname);
}

function printError(field, text) {
	field.text(text);
	field.removeClass('lvg-valid');
	field.addClass('lvg-error');
}
function print(field, text) {
	field.text(text);
	field.removeClass('lvg-error');
	field.addClass('lvg-valid');
}
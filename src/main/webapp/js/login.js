var $username = $('#login-username');
var $password = $('#login-password');
var $output = $('#output1');
var $output2 = $('#output2');
var $form = $('#form');

function isEmpty(str) {
	return (0 === str.length);
}

function checkLength(str) {
	return (str.length >= 6 && str.length < 15);
}

function checkfirstChar(str) {
	var firstChar = str.charCodeAt(0);
	return (firstChar >= 65 && firstChar <= 90) || (firstChar >= 97 && firstChar <= 192)
}

function checkUsername(str) {
	if (isEmpty(str)) {
		$output.html("Username field empty!");
		return false;
	} else if (!checkLength(str)) {
		$output.html("Username length should be between 6 and 14 characters!");
		return false;
	} else if (!checkfirstChar(str)) {
		$output.html("First character must be an alphabet!");
		return false;
	} else {
		return true;
	}
}

function checkPassword(inputtxt) {
	var passw = /^[A-Za-z]\w{5,14}$/;
	if (inputtxt.match(passw)) {
		return true;
	} else {
		$output2.html("Invalid Password Format!");
		return false;
	}
}

function sendPost(data) {
	$.ajax({
		type: 'POST',
		url: '/login',
		data: data,
		success: function(data) {
			console.log(data);
			if (data === "bad"){
                $username.val('');
                $password.val('');
				$output.html("Username or password invalid");
			}else{
                window.location = "/user";
			}
		},
		error: function() {
			// add(couple);
            // $output.html("Username or password invalid");
			console.log("NO POST");
		}
	});
}

$("#btn-login").on('click', function() {
	console.log("Login Button Clicked")
	$output.html("");
	$output2.html("");
	var couple = {
		user: $username.val(),
		pass: $password.val()
	};
	var userCheck = checkUsername(couple.user);
	var passCheck = checkPassword(couple.pass);
	if (!userCheck) {
		console.log("Username BAD");
	}
	if (!passCheck) {
		console.log("Password BAD");
	}
	if(userCheck && passCheck){
		sendPost(couple);
	}
});
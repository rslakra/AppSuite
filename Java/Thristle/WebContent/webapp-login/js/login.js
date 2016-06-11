$(document).ready(function() {
	$("#btnLogin").click(function() {
		var userName = $("#txtUserName").val();
		var password = $("#txtPassword").val();
		// Checking for blank fields.
		if( userName =='') {
			$('input[type="text"]').css("border","2px solid red");
			$('input[type="text"]').css("box-shadow","0 0 3px red");
			alert("Please provide username!");
		}else if( userName =='' || password =='') {
				$('input[type="password"]').css("border","2px solid red");
				$('input[type="password"]').css("box-shadow","0 0 3px red");
				alert("Please provide password!");
		}else {
			$.post('login',{ userName: userName, password:password},
			function(data) {
				if(data=='Invalid Username.......') {
					$('input[type="text"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
					$('input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					alert(data);
				}else if(data=='Username or Password is wrong...!!!!') {
					$('input[type="text"],input[type="password"]').css({"border":"2px solid red","box-shadow":"0 0 3px red"});
					alert(data);
				} else if(data=='Successfully Logged in...') {
					$("form")[0].reset();
					$('input[type="text"],input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					alert(data);
				} else{
					alert(data);
				}
			});
		}
	});
});
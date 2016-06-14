$(document).ready(function() {
	$("#btnLogin").click(function() {
		var userName = $("#txtUserName").val();
		var password = $("#txtPassword").val();
		// Checking for blank fields.
		if( userName =='') {
			$('input[type="text"]').css("border","2px solid red");
			$('input[type="text"]').css("box-shadow","0 0 3px red");
			alert("Please provide username!");
		}else if(password =='') {
				$('input[type="password"]').css("border","2px solid red");
				$('input[type="password"]').css("box-shadow","0 0 3px red");
				alert("Please provide password!");
		}else {
			$.post('/webAppLogin/login',{ userName: userName, password:password},
			function(data) {
				if(data.boName=='BOFailure') {
					$('input[type="text"],input[type="password"]').css({"border":"2px solid red","box-shadow":"0 0 5px red"});
					alert(data.message);
				} else if(data.boName=='BOSuccess') {
					$("form")[0].reset();
					$('input[type="text"],input[type="password"]').css({"border":"2px solid #00F5FF","box-shadow":"0 0 5px #00F5FF"});
					alert(data.success);
				} else{
					alert(data);
				}
			});
		}
	});
});
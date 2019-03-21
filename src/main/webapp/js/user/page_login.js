$(document).ready(function(){
	$("#user").blur(function(){
        if($(this).val().length == 0) {
            $("#erroruser").html("登陆名不为空");
//        	alert("登陆名不为空");
        }
    });
	$("#userPwd").blur(function() {
        if($(this).val().length == 0) {
            $("#erroruserPwd").html("密码不为空");
//        	alert("密码不为空");
        }
    });
});


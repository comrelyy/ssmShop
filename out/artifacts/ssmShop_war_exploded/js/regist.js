(function(w,undefined){
    var jsMap = {
        checkForm : function(){
            // 校验用户名:
            // 获得用户名文本框的值:
            var userName = $("#userName").val();
            if(userName == null || userName == ''){
                $("#nameTips").css("color","red");
                $("#nameTips").text("用户名不能为空!");
                return false;
            }
            // 校验密码:
            // 获得密码框的值:
            var password = $("#password").val();
            if(password == null || password == ''){
                $("#passwordTips").css("color","red");
                $("#passwordTips").text("用户名不能为空");
                return false;
            }
            // 校验确认密码:
            var repassword = $("#repassword").val();
            if(repassword != password){
                $("#repasswordTips").css("color","red");
                $("#repasswordTips").text("两次密码输入不一致!");
                return false;
            }

            //邮箱校验
            var email = $("#email").val();
            var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if(!filter.test(email)){
               $("#emailTips").css("color","red");
                $("#emailTips").text("邮箱格式不正确,请重新填写");
                return false;
            }

            return true;
        }
    };
    w.valiFun = jsMap;
})(window);

$(document).ready(function(){
    var options = {
        url :"/ssmShop/user/regist.action",
        type:"post",
        data:$("#registerForm").serialize(),
        cache:false,
        processData:false,
        contentType:false,
        beforeSubmit: valiFun.checkForm,
        success:""
    };
    $("#registerForm").submit(function(){
        $(this).ajaxSubmit(options);
        return false;
    });
});

function checkUserName(){
    debugger
    var userName = $("#userName").val();
    // $.ajax({
    //     type : "POST",
    //     url : "/ssmShop/user/checkUserName.action",
    //     contentType : "application/json",
    //     data : {"userName" : userName},
    //     async : true,
    //     cache : false,
    //     dataType : "json",
    //     success : function(data) {
    //         //alert(data.result);
    //         if ("success" == data.result) {
    //
    //             $("#nameTips").css("color", "green");
    //             $("#nameTips").text("用户名可以使用!");
    //         }else{
    //             $("#nameTips").css("color", "red");
    //             $("#nameTips").text("用户名已经被使用!");
    //         }
    //
    //     }
    // });

    $.post("/ssmShop/user/checkUserName.action",{"userName" : userName},
        function(data){
            if ("success" == data.result) {

                $("#nameTips").css("color", "green");
                $("#nameTips").text("用户名可以使用!");
            }else{
                $("#nameTips").css("color", "red");
                $("#nameTips").text("用户名已经被使用!");
            }
    },"json");
}
var Login = function () {


    //提交表单

    var formSubmit = function () {
        //var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
        $('.alert-warning', $('.login-form')).css('display', 'none');
        $('.alert-danger', $('.login-form')).css('display', 'none');
        if ($('#username').val() == '') {
            //show_err_msg('请填写您的Erp');
            $('#username').focus();
        } else if ($('#password').val() == '') {
            //show_err_msg('请填写您的密码');
            $('#password').focus();
        } else {
            var username = $("#username").val();
            var password = $("#password").val();
            var verificationCode = $("#verificationCode").val();
            password = $.md5(password);
            var url = "/students/verify";
            var param = {"username": username, "password": password, "verificationCode":verificationCode};

            $.ajax({
                url: url,
                type: 'POST',
                data: param,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        window.location.href = "/loginsucceed";
                    } else {
                        loginFail(data.errorMsg);
                    }
                }
            });
        }
    };

    var loginFail = function (failMsg) {
        $('span', $('.alert-warning')).html(failMsg);
        $('.alert-warning', $('.login-form')).show();
    };

    var handleLogin = function () {

        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-warning', $('.login-form')).hide();
                $('.alert-danger', $('.login-form')).hide();//css('display','none');
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                //form.submit(); // form validation success, call ajax form submit
                //验证成功后提交
                formSubmit();

            }
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                $('.alert-warning', $('.login-form')).css('display', 'none');
                $('.alert-danger', $('.login-form')).css('display', 'none');
                if ($('.login-form').validate().form()) {
                    formSubmit();
                    //$('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    };
    //处理忘记密码，暂时不做
    var handleForgetPassword = function () {
    };
    //处理注册，暂时不做
    var handleRegister = function () {
    };

    return {
        //main function to initiate the module
        init: function () {
            handleLogin();
            handleForgetPassword();
            handleRegister();
        }
    };

}();
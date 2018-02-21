$(document).ready(function() {
    $('#form_reg').validate({
        // правила для проверки
        rules:{
            "reg_login":{
                required:true,
                minlength:5,
                maxlength:15,
                remote: {
                    type: "post",
                    url: "/shop/login"
                }
            },
            "reg_pass":{
                required:true,
                minlength:7,
                maxlength:15
            },
            "reg_captcha":{
                required:true,
                remote: {
                    type: "post",
                    url: "/reg/check_captcha.php"
                }
            }
        },

        // выводимые сообщения при нарушении соответствующих правил
        messages:{
            "reg_login":{
                required:"Укажите Логин!",
                minlength:"От 5 до 15 символов!",
                maxlength:"От 5 до 15 символов!",
                remote: "Логин занят!"
            },
            "reg_pass":{
                required:"Укажите Пароль!",
                minlength:"От 7 до 15 символов!",
                maxlength:"От 7 до 15 символов!"
            },
            "reg_captcha":{
                required:"Введите код с картинки!",
                remote: "Не верный код проверки!"
            }
        },
        submitHandler: function(form){
            $(form).ajaxSubmit({
                success: function(data) {
                    if (data == 'true') {
                        $("#block-form-registration").fadeOut(300,function() {
                            $("#reg_message").addClass("reg_message_good").fadeIn(400).html("Вы успешно зарегистрированы!");
                            $("#form_submit").hide();

                        });
                    }
                    else                    {
                        $("#reg_message").addClass("reg_message_error").fadeIn(400).html(data);
                    }
                }
            });
        }
    });
});

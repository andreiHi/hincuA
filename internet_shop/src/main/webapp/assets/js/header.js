$(document).ready(function () {

    $('.top-auth').click(
        function () {
            if($('#block-top-auth').css('display') == 'none'){
                $('.top-auth').attr('id','activ-button');
                $('#block-top-auth').fadeIn(200);
            } else {
                $('#block-top-auth').fadeOut(200);
                $('.top-auth').attr('id','');
            }
        });
    $('#button-pass-show-hide').click(function () {
        var statuspass = $('#button-pass-show-hide').attr('class');
        if (statuspass=='pass-show'){
            $('#button-pass-show-hide').attr('class','pass-hide');
            var $input = $('#auth_pass');
            var change = "text";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id", $input.attr("id"))
                .attr("name", $input.attr("name"))
                .attr('class', $input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        }else {
            $('#button-pass-show-hide').attr('class','pass-show');
            var $input = $("#auth_pass");
            var change = "password";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id", $input.attr("id"))
                .attr("name", $input.attr("name"))
                .attr('class', $input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        }
    });
    $("#button-auth").click(function() {
        var auth_login = $("#auth_login").val();
        var auth_pass = $("#auth_pass").val();
        var send_login,send_pass,auth_rememberme;
        if (auth_login == "" || auth_login.length > 30 ) {
            $("#auth_login").css("borderColor","#FDB6B6");
            send_login = 'no';
        }else {
            $("#auth_login").css("borderColor","#DBDBDB");
            send_login = 'yes';
        }
        if (auth_pass == "" || auth_pass.length > 15 ) {
            $("#auth_pass").css("borderColor","#FDB6B6");
            send_pass = 'no';
        }else { $("#auth_pass").css("borderColor","#DBDBDB");
            send_pass = 'yes'; }

        if ($("#rememberme").prop('checked')) {
            auth_rememberme = 'yes';

        }else {
            auth_rememberme = 'no';
        }

        if ( send_login == 'yes' && send_pass == 'yes' ) {
            $("#button-auth").hide();
            $(".auth-loading").show();

            $.ajax({
                async: true,
                method: "POST",
                url: "/shop/authorization",
                data:{login:auth_login, password:auth_pass, remember:auth_rememberme},
               cache: false,
                complete: function(data) {
                     var res = JSON.parse(data.responseText);
                     if (res === true) {
                         $('#reg-auth-title').replaceWith('<p id="auth-user-info" align="right"><img src="assets/imj/user.png"/>Здравствуйте,'+auth_login +'</p>');
                         //location.reload();
                         $('#block-top-auth').hide();

                     }else {
                        $("#message-auth").slideDown(400);
                        $(".auth-loading").hide();
                        $("#button-auth").show();
                    }
                }
            });
        }
    });
});
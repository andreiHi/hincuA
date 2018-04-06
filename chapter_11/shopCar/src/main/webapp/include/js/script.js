$(document).ready(function () {
    $('#header').load('include/html/header.html');
});

var user ={};
$(document).on('click', '#intro', function () {
    $('#form').load('include/html/login.html');
});

$(document).on('click', '#signup', function () {
    $('#form').load('include/html/sing.html');
});
$(document).on('click', '#index', function () {
    location.href='index.html';
});

$(document).on('click', '#submit', function () {
    if (user.login === undefined) {
        $('#form').load('include/html/login.html');
    } else {
        location.href='add.html';
    }
});

//проверка на заполненность всех данных объявления кроме описания.
$('#form_add').submit(function () {
    var valid = true;
    var select = document.getElementsByTagName('select');
    for (var i = 0; i<select.length; i++) {
        if(select[i].options[select[i].selectedIndex].value === '0') {
            valid = false;
            $(select[i]).css('background-color', 'red');
            return false;
        } else {
            $(select[i]).css('background-color', 'white');
        }
    }
    var form = $(this);
    form.find('input').each(function () {
        if ($(this).val()==='') {
            valid = false;
            $(this).css('background-color', 'red');
            return false;
        } else {
            $(this).css('background-color', 'white');
        }
    });
    if (valid) {
        $('#form_add').fadeOut(300,function () {
            $('#message').addClass('message_good').fadeIn(400).html("Объявление успешно добавлено.")
        });
    } else {
        $("#message").addClass("message_error").fadeIn(400).html("Введите все данные!");
    }
    return false;
});
//регистрация
$(document).on('submit', '#sign', function () {
    var psw = $('#psw').val();
    var psw_repeat = $('#psw-repeat').val();
    if (psw !== psw_repeat) {
        $('#info').replaceWith('<p id="info" class="message_error" >Passwords do not match</p>');
        return false;
    } else {
        var newUser = {};
        newUser.login = $('#login').val();
        newUser.password = psw;
        newUser.email = $('#email').val();
        newUser.phone = $('#phone').val();
        ajax('sing', newUser, function (data) {
            if (data ==='ok') {
                $('#forma').hide();
                $('#info').replaceWith('<p id="info" class="message_ok">The account was created successfully</p>');
            } else {
                $('#info').replaceWith('<p id="info" class="message_error" >' + data + '</p>');
            }
        });
        return false;
    }
});
function ajax(action, data, toDo) {
    $.ajax({
        method:'POST',
        url: "/CarShop/data",
        data: JSON.stringify({
            action: action,
            data: data
        }),
        success: toDo
    });
}
//авторизация
$(document).on('submit', '#login', function () {
    var login = {};
    login.login = $('#uname').val();
    login.password = $('#psw').val();
    ajax("login", login, function (data) {
        if (data === true) {
            user.login = login.login;
            $('#id01').hide();
        } else {


        }

    });
    return false;

});

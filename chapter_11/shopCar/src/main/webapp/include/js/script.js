$(document).ready(function () {
    $('#header').load('include/html/header.html');
    getComponents();
});

const user = {};
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
                $('#forma').empty();
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
        url: "/data",
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
    ajax("logInOut", login, function (data) {
        if (data === true) {
            user.login = login.login;
            location.reload();
        } else {
            $('#info-login').replaceWith('<p class="message_error" >Login or Password are incorrect</p>');
        }
    });
    return false;
});
function getComponents() {
    ajax('getItems', {}, function (data) {
        console.log(data);
        setLogin(data['user']);
        addOptions('transmission', data['transmission']);
        addOptions('gearBox',      data['gearBox']);
        addOptions('carcass',      data['carcass']);
        addOptions('engineType',   data['engineType']);
        addItems('brand',          data['brands']);
    });
}
function addOptions(id, data) {
    $.each(data, function (key, value) {
        $('#' + id).append($('<option></option>')
            .attr('value', value)
            .text(value.replace(/_/g,' ')));//регулярка заменяет подчеркивание на пробел
    });
}

function addItems(id, data) {
    var json = JSON.parse(data);
    $.each(json, function (key, value) {
        $('#' + id).append($('<option></option>')
            .attr('value', json[key].id)
            .text(value.name));
    });
}

function setLogin(data) {
    if (data.login != null) {
        user.login = data.login;
        $('#id01').hide();
        $('#intro').replaceWith('<button id="name_user"  style="width:auto;">Hello, '+user.login+'!</button>');
        $('#signup').replaceWith('<button id="user_log_out"  style="width:auto;">Log out</button>');
    }
}
$(document).on('click', '#user_log_out', function () {
    ajax('logInOut',{}, function () {
        location.href='index.html';
    })
});

$('#brand').change(function () {
    var id = $(this).val();
    var idBrand = {};
    idBrand.id = id;
    if (id === '0') {
        $('#model').html('<option value="0">-Модель-</option>');
        $('#model').attr('disabled', true);
    } else {
        $('#model').html('<option value="0">-Модель-</option>');
        $('#model').attr('disabled', false);
        ajax('getModels', idBrand, function (data) {
            var models = data['models'];
            addItems('model', models);
        })
    }
});
$('#engineType').change(function () {
    var id = $(this).val();
    if (id ==='Electro') {
    $('#volumeDiv').hide();
    $('#volume').hide();
    } else {
        $('#volume').show();
        $('#volumeDiv').show();
    }
});


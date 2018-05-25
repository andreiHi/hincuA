$(document).ready(function () {
    $('#header').load('include/html/header.html');
   // getComponents();
});

$(document).on('click', '#intro', function () {
    $('#form').load('include/html/login.html');
});

$(document).on('click', '#signup', function () {
    $('#form').load('include/html/sing.html');
});
$(document).on('click', '#index', function () {
    location.href='index.html';
});

$(document).on('click', '#addNew', function () {
        location.href='add.html';
});
$(document).on('click', '#update', function () {
        location.href='update.html';
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
        ajaxS('user/registration', newUser, function (data) {
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

function ajaxS(url, data, todo) {
    $.ajax({
        method:'POST',
        url: '/' +  url,
        contentType:"application/json",
        data: JSON.stringify(data),
        success: todo
    });
}
function ajaxGet(url, data, todo) {
    $.ajax({
        method:"GET",
        url:'/' + url,
        contentType:"application/json",
        data: data,
        success: todo
    });
}
//авторизация
$(document).on('submit', '#login', function () {
    var login = {};
    login.login = $('#uname').val();
    login.password = $('#psw').val();
    ajaxS("user/login", login, function (data) {
        if (data === true) {
            setLogin(login);
        } else {
            $('#info-login').replaceWith('<p class="message_error" >Login or Password are incorrect</p>');
        }
    });
    return false;
});
//получение всех фильтров и поддержание ссесии
function getComponents() {
    ajaxS('getItems', {}, function (data) {
        // console.log(data);
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
        $('#id01').hide();
        $('#intro').replaceWith('<button id="name_user"  style="width:auto;">Hello, '+ data.login +'!</button>');
        $('#signup').replaceWith('<button id="user_log_out"  style="width:auto;">Log out</button>');
        document.getElementById('update').style.display = 'block';
    } else {
        $('#header').load('include/html/header.html');
    }
}
//выход из сессии
$(document).on('click', '#user_log_out', function () {
        ajaxS('user/logOut', {}, function () {
            console.log(window.location.href);
            if (window.location.href.includes('index.html')) {
                //todo with update page
                setLogin({});
            } else {
                location.href = 'index.html';
            }
        })
});
$(document).on('change', '#brand', function () {
    var id = $(this).val();
    if (id === '0') {
        $('#model').html('<option value="0">-Модель-</option>');
        $('#model').attr('disabled', true);
    } else {
        $('#model').html('<option value="0">-Модель-</option>');
        $('#model').attr('disabled', false);

        ajaxGet('getModels', {id:parseInt(id)}, function (data) {
            var models = data['models'];
            addItems('model', models);
        })
    }
});




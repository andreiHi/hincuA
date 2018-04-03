$(document).ready(function () {

});
// Get the modal
var modal = document.getElementById('id01');
var modal2 = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal || event.target == modal2) {
        modal.style.display = "none";
        modal2.style.display="none";
    }
};
var user ={};

$('#intro').click(function () {
    $('#form').load('include/html/login.html');
});
$('#sign').click(function () {
    $('#form').load('include/html/sing.html');
});

$('#submit').click(function () {
    if (user === undefined) {
        $('#form').load('include/html/login.html');
    } else {
        location.href='add.html';
    }
});
$('#index').click(function () {
    location.href='index.html';
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
$('#sign').submit(function () {
    // if (document.getElementById('#psw').value!==document.getElementById('#).value) {
    //     console.log("eror")
    // }

    alert('sss');
});
$(document).on('submit', '#sign', function () {
    var psw = $('#psw').val();
    var psw_repeat = $('#psw-repeat').val();
    if (psw !== psw_repeat) {
        $('#info').replaceWith('<p class="message_error" style="height: 15px">Passwords do not match</p>');
        return false;
    }

});
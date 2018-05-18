$(document).ready(function () {
    getComponents();
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
        if ($(this).val()==='' && $(this).css('display') !== 'none' && $(this).attr('name') !== 'photo') {
            valid = false;
            $(this).css('background-color', 'red');
            return false;
        } else {
            $(this).css('background-color', 'white');
        }
    });
    if (valid) {
        $.ajax({
            url: "/create",
            type: "POST",
            contentType: false,
            processData: false,
            cache:false,
            enctype: 'multipart/form-data',
            data: new FormData(document.forms.form_add),
            success: function(json){
                if (json !=='login') {
                    $('#form_add').fadeOut(300,function () {
                        $('#message').addClass('message_good').fadeIn(400).html("Объявление успешно добавлено.");
                        $('#advert-list').empty();
                    });
                } else {
                    $("#message").addClass("message_error").fadeIn(400).html("Re login again, please.");
                }
            },
            error:function (err) {
                alert(err);
            }
        });
    } else {
        $("#message").addClass("message_error").fadeIn(400).html("Введите все данные!");
    }
    return false;
});
$('#engineType').change(function () {
    var id = $(this).val();
    if (id ==='Electro') {
        document.getElementById('volume').value = 0;
        $('#volumeDiv').hide();
        $('#volume').hide();
    } else {
        $('#volume').show();
        $('#volumeDiv').show();
    }
});
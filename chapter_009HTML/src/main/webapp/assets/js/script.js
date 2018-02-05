$(document).ready(function () {
    showtowns();
    $('#country_id').change(selectcity());

    $("#ajax").submit(function () {
        var error = false;
        var form = $(this);
        form.find('input').each(function () {
            if ($(this).val()=='') {
                error = true;
                alert('Заполните поле ' +$(this).attr('name') + '!');
                $(this).css('background-color', 'red');
            }
        });
        if (!error) {
            // var f = form.serialize();
        }
    });

});

function showtowns() {
    $.ajax({
        method: "get",
        url: '/items/json',
        data: {select :'country'},
        complete:function (result) {
            var adsress = JSON.parse(result.responseText);
            var options = '';
            for (var i=0; i<adsress.length; i++) {
                options+='<option value="' + adsress[i].id + '">' + adsress[i].name + '</option>';
            }
           $('#country_id').html('<option value="0">- Выберите страну -</option>'+options);
        }
    });
}
function selectcity() {
  alert.console.log('country was selected');
}

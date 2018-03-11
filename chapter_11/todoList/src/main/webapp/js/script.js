$(document).ready(function () {
$.ajax({
    method:"POST",
    url:"/todo/items",
    data:{'status':'not'},
    complete:function (d) {
        console.log(d);
        var item = JSON.parse(d.responseText);
        console.log(item);
        var tab='<TABLE align="center" CELLPADDING="10" WIDTH="100%">';
        tab+='<TR><TH style="width: 15%">Data</TH><TH style="width: 70%">Description</TH><TH style="width: 15%">Status</TH></TR>';
        for (var i=0;i<item.length; i++) {
            tab+='<tr><td align="center" class="data">'+item[i].created+'</td><td align="center">'+ item[i].description+'</td><td align="center"><input class="check"  type="checkbox"></td><tr>';
        }
        $('#tab_items').html(tab);

    }
});
});
function showDone(id){
    var check = $('#'+id).prop('checked');
if (check) {
    console.log('f');
    $.ajax({
        method:"POST",
        url:"/todo/items",
        data:{'status':'all'},
        complete:function (d) {
            var item = JSON.parse(d.responseText);
            var tab='<TABLE align="center" CELLPADDING="10" WIDTH="100%">';
            tab+='<TR><TH style="width: 15%">Data</TH><TH style="width: 70%">Description</TH><TH style="width: 15%">Status</TH></TR>';

            for (var i=0;i<item.length; i++) {
                if (item[i].done) {
                    tab += '<tr><td align="center" class="data">' + item[i].created + '</td><td align="center">' + item[i].description + '</td><td align="center"><input class="check" id="' + item[i].id + '" type="checkbox" checked="checked"></td><tr>';
                } else {
                    tab += '<tr><td align="center" class="data">' + item[i].created + '</td><td align="center">' + item[i].description + '</td><td align="center"><input class="check" id="' + item[i].id + '" type="checkbox"></td><tr>';

                }
            }
            $('#tab_items').html(tab);

        }
    });
} else {
    $.ajax({
        method:"POST",
        url:"/todo/items",
        data:{'status':'not'},
        complete:function (d) {
            console.log(d);
            var item = JSON.parse(d.responseText);
            console.log(item);
            var tab='<TABLE align="center" CELLPADDING="10" WIDTH="100%">';
            tab+='<TR><TH style="width: 15%">Data</TH><TH style="width: 70%">Description</TH><TH style="width: 15%">Status</TH></TR>';
            for (var i=0;i<item.length; i++) {
                tab+='<tr><td align="center" class="data">'+item[i].created+'</td><td align="center">'+ item[i].description+'</td><td align="center"><input class="check"  type="checkbox"></td><tr>';
            }
            $('#tab_items').html(tab);

        }
    });
}
}



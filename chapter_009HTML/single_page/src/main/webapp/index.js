$(document).ready(function () {
    console.log('hello world');
  // getJsonPersons();
});
function validate(field) {
    if (field.val().trim() === "") {
        alert("Fill  Field " + field.attr('name').toUpperCase());
        field.focus();
        return false;
    }
    return true;
}
function getJsonPersons() {
    $.ajax({
        url: "./json",
        method: "get",
        complete :function (data) {
            var data = JSON.parse(data.responseText);
            $("tr:has(td)").remove();
            var trHTML = '';
            $.each(data, function (i, item) {
                trHTML += '<tr>' +
                    '<td>' + data[i].name + '</td>' +
                    '<td>' + data[i].surname + '</td>' +
                    '<td>' + data[i].gender + '</td>' +
                    '<td>' + data[i].desc + '</td>' +
                    '</tr>';
            });
            $('#table').append(trHTML);
        }
    })
}
function sendJsonPerson() {
    var arr = [$("#name"), $("#surname"), $("#gender"), $("#description")];
    if (arr.every(validate)) {
        var person = {};
        person.name = arr[0].val();
        person.surname = arr[1].val();
        person.gender = arr[2].val();
        person.desc = arr[3].val();
        $.ajax({
            type: "post",
            url: "json",
            contentType: "json; charset=utf-8",
            data: JSON.stringify(person),
            complete: getJsonPersons()
        });
    }
}

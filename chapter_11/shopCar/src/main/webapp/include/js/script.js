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
var user ={name: 'aaa'};

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
$(document).ready(function () {
    getAdverts();
});

function getAdverts(data) {
    var conditions ={};
    if (data === undefined) {
        conditions.get = 'all';
    }
    ajax('allAds', conditions, function (respons) {
        console.log(respons);
    })
}
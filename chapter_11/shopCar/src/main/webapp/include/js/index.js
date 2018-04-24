$(document).ready(function () {
    var adverts = "all";
    getAdverts(adverts);
});
function getAdverts(data) {
    var conditions ={};
    if (data === undefined) {
        conditions.get = 'all';
    } else {
        conditions.get = data;
    }
    ajax('allAds', conditions, function (respons) {
        var ul = document.createElement("ul");
        ul.setAttribute('id', 'block-ad-list');
        $.each(respons, function (k, v) {
            var image = v["car"]["images"]["image"].largeImage;
            console.log(image);
            console.log(v["car"]["images"]["image"]);
            var tr = v['car'].transmission.replace(/_/g, ' ');
            var li = ul.appendChild(document.createElement('li'));
            var div = li.appendChild(document.createElement('div'));
            div.classList.add('block-images-list');
            var img = div.appendChild(document.createElement('img'));
            img.setAttribute('src','/img?name=' + v["car"]["images"]["image"].smallImage +'');
            img.setAttribute('height','200px');
            img.setAttribute('width','200px');
            img.setAttribute('id', image);
            img.setAttribute('onclick', 'getLargePhoto('+ 'ssss' +')');
            let p = li.appendChild(document.createElement('p'));
            p.classList.add('style-title-list');
            let a = document.createElement("a");
            a.setAttribute('id', k);
            a.setAttribute('href', 'index.html');
            a.append(v['car']['brand'].name + ' ' + v['car']['model'].name + ', ' + v['car']['year']);
            p.append(a);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-price-list');
            p.append('Price ' + v.price + ' руб.');
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text-list');
            p.append(v['car'].mileage +' км, ' + v['car']['engine'].volume + ' cm3, ('
                + v['car']['engine'].power+' л.с.), ' + v['car'].carcass+', ' + tr + ', '
                + v['car']['engine'].fuelType);
            div = li.appendChild(document.createElement('div'));
            div.classList.add('style-text-list');
            div.append(v.description);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text');
            p.append('Phone: '+ v['user'].phone);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text');
            p.append('Data: ' + v.data);
            if (v.state === 'SOLD') {
                p = li.appendChild(document.createElement('p'));
                p.classList.add('sold_out');
                p.append('Sold Out!');
            }
        });
        $('#advert-list').append(ul);
    })
}
function getLargePhoto(d) {
    console.log(d)
}
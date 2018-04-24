$(document).ready(function () {
    var byUser = 'byUser';
    getAdvertsByUser(byUser)
});
function getAdvertsByUser(data) {
    var conditions ={};
    if (data === undefined) {
        conditions.get = 'all';
    } else {
        conditions.get = data;
    }
    ajax('allAds', conditions, function (respons) {
        var ul = document.createElement("ul");
        ul.setAttribute('id', 'block-ad-list');
        $.each(respons, function (k,v) {
            let tr = v['car'].transmission.replace(/_/g, ' ');
            let li = ul.appendChild(document.createElement('li'));
            let div = li.appendChild(document.createElement('div'));
            div.classList.add('block-images-list');
            let img = div.appendChild(document.createElement('img'));
            img.setAttribute('src','/img?name=' + v["car"]["images"]["image"].largeImage +'');
            img.setAttribute('height','200px');
            img.setAttribute('width','200px');
            let p = li.appendChild(document.createElement('p'));
            p.classList.add('style-title-list');
            let a = document.createElement("a");
            a.setAttribute('id', k);
            a.setAttribute('href', 'index.html');
            a.append(v['car']['brand'].name + ' ' + v['car']['model'].name + ', ' + v['car']['year']);
            p.append(a);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-price-list');
            p.append('Price '+ v.price +' руб.');
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text-list');
            p.append(v['car'].mileage +' км, '+v['car']['engine'].volume+' cm3, ('
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
            let ul2 = li.appendChild(document.createElement('ul'));
            ul2.classList.add('buttons_new_sold');
            let li2 = ul2.appendChild(document.createElement('li'));
            p = li2.appendChild(document.createElement('a'));
            p.classList.add('state');
            p.append(v.state);
            li2 = ul2.appendChild(document.createElement('li'));
            p = li2.appendChild(document.createElement('button'));
            p.setAttribute('id','setSold');
            p.setAttribute('Car_id', k);
            p.append('Set sold');
            li2 = ul2.appendChild(document.createElement('li'));
            p = li2.appendChild(document.createElement('button'));
            p.setAttribute('id','setNew');
            p.setAttribute('Car_id', k);
            p.append('Set New');
        });
        $('#advert-list').append(ul);
    })
}
$(document).on('click','#setSold', function () {
    var car = {};
    car.id = $(this).attr('Car_id');
    car.state = 'SOLD';
    ajax('setSold', car, function (data) {
        if(data === true) {
            $('#advert-list').empty();
            getAdvertsByUser('byUser');
        }
    })
});

$(document).on('click','#setNew', function () {
    var car = {};
    car.id = $(this).attr('Car_id');
    car.state = 'NEW';
    ajax('setSold', car, function (data) {
        if(data === true) {
            $('#advert-list').empty();
            getAdvertsByUser('byUser');
        }
    })
});
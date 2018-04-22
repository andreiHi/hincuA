$(document).ready(function () {
    getAdverts();
});

function getAdverts(data) {
    var conditions ={};
    if (data === undefined) {
        conditions.get = 'all';
    }
    ajax('allAds', conditions, function (respons) {
        var ul = document.createElement("ul");
        ul.setAttribute('id', 'block-ad-list');
        $.each(respons, function (k,v) {
            var tr = v['car'].transmission.replace(/_/g,' ');
            var li = ul.appendChild(document.createElement('li'));
            var div = li.appendChild(document.createElement('div'));
            div.classList.add('block-images-list');
            var img = div.appendChild(document.createElement('img'));
            img.setAttribute('src','img?name=' + v["car"]["images"]["image"].path +'');
            img.setAttribute('height','200px');
            img.setAttribute('width','200px');
            var p = li.appendChild(document.createElement('p'));
            p.classList.add('style-title-list');
            var a = document.createElement("a");
            a.setAttribute('id', k);
            a.setAttribute('href', 'index.html');
            a.append(v['car']['brand'].name +' ' +v['car']['model'].name +', '+v['car']['year']);
            p.append(a);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-price-list');
            p.append('Price '+ v.price +' руб.');
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text-list');
            p.append(v['car'].mileage +' км, '+v['car']['engine'].volume+' cm3, ('
            +v['car']['engine'].power+' л.с.), '+v['car'].carcass+', '+tr+', '
            +v['car']['engine'].fuelType);
            div = li.appendChild(document.createElement('div'));
            div.classList.add('style-text-list');
            div.append(v.description);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text');
            p.append('Phone: '+v['user'].phone);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text');
            p.append('Data: '+ v.data);
        });

        $('#product-list').append(ul);
    })
}
// <ul id="block-ad-list">
//     <li>
//     <div class="block-images-list">
//     <img src="imj.png" height="200px" width="200px" />
//     </div>
//     <p class="style-title-list" >
//     <a href='index.html'>Model of car, year-2014</a></p>
// <p class="style-price-list" >Price <strong>20000</strong> руб.</p>
// <p class="style-text-list">46 000 км, 3.0 AT (249 л.с.), внедорожник, полный, бензин</p>
// <div class="style-text-list" >description
//     </div>
//     </li>
//     </ul>
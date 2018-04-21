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
            var id = k;
            var advert = v;
            var li = ul.appendChild(document.createElement('li'));
            var div = li.appendChild(document.createElement('div'));
            div.classList.add('block-images-list');
            li = div.appendChild(document.createElement('img'));
            li.setAttribute('src','img?name=' + advert["car"]["images"]["image"].path +'');
            li.setAttribute('height','200px');
            li.setAttribute('width','200px');
            li  = div.appendChild(document.createElement('p'));
            li.classList.add('style-title-list');
            var a = document.createElement("a");
            a.setAttribute('id', k);
            a.setAttribute('href', 'index.html');
            a.append(advert['car']['model'].name );
            li.append(a);

            // ul.append('<p class="style-title-list">');
            // ul.append('<a id="'+ k +'" href="index.html">' + advert['car']['model'].name + '</a></p>');
            // ul.append('<p class="style-price-list" >Price <strong>20000</strong> руб.</p>');
            // ul.append('</li>')
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
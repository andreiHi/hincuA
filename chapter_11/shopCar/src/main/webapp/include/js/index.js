$(document).ready(function () {
    getAdverts();
});

function getAdverts(data) {
    var conditions ={};
    if (data === undefined) {
        conditions.get = 'all';
    }
    ajax('allAds', conditions, function (respons) {
        $.each(respons, function (k,v) {
           var id = k;
           var advert = v;
        })
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
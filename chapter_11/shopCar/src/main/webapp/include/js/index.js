$(document).ready(function () {
    getAdverts();
});
function getAdverts(data) {
    var conditions = {};
    if (data === undefined) {
        var v  = {};
        v.image = false;
        v.today = false;
         conditions = v;
    } else {
        conditions = data;
    }
    ajax('allAds', conditions, function (respons) {
        var ul = document.createElement("ul");
        ul.setAttribute('id', 'block-ad-list');
        $.each(respons, function (k, v) {
            var tr = v['car'].transmission.replace(/_/g, ' ');
            var li = ul.appendChild(document.createElement('li'));
            var div = li.appendChild(document.createElement('div'));
            div.classList.add('block-images-list');
            if (v.car.images.image !== undefined) {
                var img = div.appendChild(document.createElement('img'));
                img.setAttribute('src', '/img?name=' + v.car.images.image.smallImage);
                img.setAttribute('height', '200px');
                img.setAttribute('width', '200px');
                img.setAttribute('iid', v.car.images.image.largeImage);
                img.classList.add('onClick');
            }
            let p = li.appendChild(document.createElement('p'));
            p.classList.add('style-title-list');
            let a = document.createElement("a");
            a.setAttribute('id', k);
            a.setAttribute('href', 'index.html');
            a.append(v.car.brand.name + ' ' + v.car.model.name + ', ' + v.car.year);
            p.append(a);
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-price-list');
            p.append('Price ' + v.price + ' руб.');
            p = li.appendChild(document.createElement('p'));
            p.classList.add('style-text-list');
            p.append(v.car.mileage +' км, ' + v.car.engine.volume + ' cm3, ('
                + v.car.engine.power+' л.с.), ' + v.car.carcass+', ' + tr + ', '
                + v.car.engine.fuelType);
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
        $('#advert-list').empty();
        $('#advert-list').append(ul);
    })
}

$(document).on('click', '.onClick', function () {
    var id = $(this).attr('iid');
    $('#form').html(
        "<div class='modal'>" +
        "<div class='modal-content animate'>" +
        "<div class='imgcontaine'>" +
        "<span onclick= $('#form').empty() class='close_img' >x</span>" +
        "<img src='img?name="+id+"' alt='Avatar' class='avatar'>" +
        "</div>"+
        "</div></div>")
});
$(document).on('change', ':checkbox', function () {
    var checkToday = $('#today').prop('checked');
    var checkWithPhoto = $('#withPhoto').prop('checked');
    var check = {};
    check.today = checkToday;
    check.image = checkWithPhoto;
    var select = document.getElementsByTagName('select');
    for (var i = 0; i < select.length; i++) {
        if(select[i].options[select[i].selectedIndex].value !== '0') {
            check[select[i].name] = select[i].options[select[i].selectedIndex].value;
        }
    }
    var form = $(this);
    form.find('input').each(function () {
        if ($(this).val()!=='') {
            check[$(this).attr('name')] = $(this).val();
        }
    });
    getAdverts(check);
});
$('#search_block').submit(function () {
    var checkToday = $('#today').prop('checked');
    var checkWithPhoto = $('#withPhoto').prop('checked');
    var check = {};
    check.today = checkToday;
    check.image = checkWithPhoto;
    var selected = false;
    var select = document.getElementsByTagName('select');
    for (var i = 0; i < select.length; i++) {
        if(select[i].options[select[i].selectedIndex].value !== '0') {
            check[select[i].name] = select[i].options[select[i].selectedIndex].value;
            selected = true;
        }
    }
    var form = $(this);
    form.find('input').each(function () {
        if ($(this).val()!=='') {
            check[$(this).attr('name')] = $(this).val();
            selected = true;
        }
    });

    if (selected === true) {
        getAdverts(check);
    }
    return false;
});
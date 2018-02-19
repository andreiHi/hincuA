$(document).ready(function () {
    $('#block-header').load("assets/include/block-header.html");
    $('#block-category').load("assets/include/block-category.html");
    $('#block-parameter').load("assets/include/block-parameter.html");
    $('#block-footer').load("assets/include/block-footer.html");

    $('#style-grid').click(function () {
       // showProductsGrid();
        $('#block-tovar-grid').show();
        $('#block-tovar-list').hide();
        $('#style-grid').attr('src', 'assets/imj/icon-grid-active.png');
        $('#style-list').attr('src', 'assets/imj/icon-list.png');
        $.cookie('select_style', 'grid');
    });
    $('#style-list').click(function () {
        $('#block-tovar-grid').hide();
        $('#block-tovar-list').show();
        //showProductsList();
        $('#style-list').attr('src', 'assets/imj/icon-list-active.png');
        $('#style-grid').attr('src', 'assets/imj/icon-grid.png');
        $.cookie('select_style', 'list');
    });
    if ($.cookie('select_style') =='list') {
      //  showProductsList();
        $('#block-tovar-grid').hide();
        $('#block-tovar-list').show();
        $('#style-list').attr('src', 'assets/imj/icon-list-active.png');
        $('#style-grid').attr('src', 'assets/imj/icon-grid.png');
    } else if ($.cookie('select_style') =='grid') {
       // showProductsGrid();
        $('#block-tovar-grid').show();
        $('#block-tovar-list').hide();
        $('#style-grid').attr('src', 'assets/imj/icon-grid-active.png');
        $('#style-list').attr('src', 'assets/imj/icon-list.png');
    } else if ($.cookie('select_style') ==null) {
        alert('null');
        showProductsGrid();
        $('#block-tovar-list').hide();
       // showProductsList();

    }

    $('#select-sort').click(function () {
        $('#sorting-list').slideToggle(200);
    });

    $('#options-list li').click(function () {
            var id = $(this).attr('id');
            if ($.cookie('select_style') == 'list') {
                showProductsList(id);
            } else {
                showProductsGrid(id);
            }
        }
    );
    $(function() {
        $("#newsticker").jCarouselLite({
            vertical:true,
            hoverPause:true,
            btnNext: "#news-next",
            btnPrev: "#news-prev",
            visible:3,
            auto:3000,
            speed:500
        });
    });
});
function showProductsGrid(id) {
    $.ajax({
        method:"GET",
        url:"/shop/products",
        data:{product:"all", sort:id},
        complete:function (data) {
            var product = JSON.parse(data.responseText);
            var li ='<ul id="block-tovar-grid">';
            for (var i =0; i<product.length; i++ ) {
                li += '<li><p class="style-title-grid"><a href="http:/shop/mobile/' + product[i].id + '">' + product[i].name + '</a></p>';
                //отзывы и просмотры
                li += '<ul class="reviews-and-counts-grid">';
                li += '<li><img src="assets/imj/eye-icon.png"/><p>'+product[i].views+'</p></li>';
                li += '<li><img src="assets/imj/comment-icon.png" /><p>0</p></li>';
                li += '</ul>';
                //кнопка корзины
                li += '<a class="add-cart-style-grid" tid="'+ product[i].id +'" ></a>';
                li += '<p class="style-price-grid" ><strong>' + product[i].price + '</strong> руб.</p>';
                li+= '<div class="mini-features" >' + product[i].miniDescription + '</div>';
                li += '</li>';
            }
            $('#product-grid').html(li + '</ul>');
        }
    });
}
function showProductsList(id) {
    $.ajax({
        method:"GET",
        url:"/shop/products",
        data:{product:"all", sort:id},
        complete:function (data) {
            var product = JSON.parse(data.responseText);
            var li ='<ul id="block-tovar-list">';
            for (var i =0; i<product.length; i++ ) {
                li += '<li><p class="style-title-list"><a href="http:/shop/mobile/' + product[i].id + '">' + product[i].name + '</a></p>';
                //отзывы и просмотры
                li += '<ul class="reviews-and-counts-list">';
                li += '<li><img src="assets/imj/eye-icon.png"/><p>'+product[i].views+'</p></li>';
                li += '<li><img src="assets/imj/comment-icon.png" /><p>0</p></li>';
                li += '</ul>';
                //кнопка корзины
                li += '<a class="add-cart-style-list" tid="'+ product[i].id +'" ></a>';
                li += '<p class="style-price-list" ><strong>' + product[i].price + '</strong> руб.</p>';
                li+= '<div class="style-text-list" >' + product[i].description + '</div>';
                li += '</li>';
            }
            $('#product-list').html(li + '</ul>');
        }
    });
}
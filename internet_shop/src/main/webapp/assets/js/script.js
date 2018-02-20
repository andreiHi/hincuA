$(document).ready(function () {
    $('#block-header').load("assets/include/block-header.html");
    $('#block-category').load("assets/include/block-category.html");
     $('#block-parameter').load("assets/include/block-parameter.html");
    $('#block-footer').load("assets/include/block-footer.html");

    if ($.cookie('select_style')==null) {
        $.cookie('select_style', 'grid');
        $('#style-grid').attr('src', 'assets/imj/icon-grid-active.png');
        $.cookie('sort','Without_Sorting');
        showProductsGrid($.cookie('sort'));
    } else if ($.cookie('select_style')=='grid') {
        $('#block-tovar-grid').show();
        $('#block-tovar-list').hide();
        $('#style-grid').attr('src', 'assets/imj/icon-grid-active.png');
        $('#style-list').attr('src', 'assets/imj/icon-list.png');
        showProductsGrid($.cookie('sort'));
    } else if ($.cookie('select_style')=='list') {
        $('#block-tovar-grid').hide();
        $('#block-tovar-list').show();
        $('#style-list').attr('src', 'assets/imj/icon-list-active.png');
        $('#style-grid').attr('src', 'assets/imj/icon-grid.png');
        showProductsList($.cookie('sort'));
    }

    $('#options-sort li').click(function () {
            $.cookie('sort',$(this).attr('id')) ;
            if ($.cookie('select_style')=='grid') {
                showProductsGrid($.cookie('sort'));
            }
            if ($.cookie('select_style')=='list') {
                showProductsList($.cookie('sort'));
            }
        }
    );
    $('#style-list').click(function () {
        showProductsList($.cookie('sort'));
        $('#block-tovar-grid').hide();
        $('#block-tovar-list').show();
        $('#style-list').attr('src', 'assets/imj/icon-list-active.png');
        $('#style-grid').attr('src', 'assets/imj/icon-grid.png');
        $.cookie('select_style', 'list');
    });

    $('#style-grid').click(function () {
         showProductsGrid($.cookie('sort'));
        $('#block-tovar-grid').show();
        $('#block-tovar-list').hide();
        $('#style-grid').attr('src', 'assets/imj/icon-grid-active.png');
        $('#style-list').attr('src', 'assets/imj/icon-list.png');
        $.cookie('select_style', 'grid');
    });
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
    // $(document).ready(function() {
        $('#blocktrackbar').trackbar({
            onMove: function () {
                document.getElementById("start-price").value = this.leftValue;
                document.getElementById("end-price").value = this.rightValue;
            },
            width: 160,
            leftLimit: 1000,
            leftValue: 1000,
            rightLimit: 50000,
            rightValue:30000,
            roundUp:1000
        });
    // });
});
function showProductsGrid(id) {
    $.ajax({
        method:"GET",
        url:"/shop/products",
        data:{sort:id},
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
                li += '<a class="add-cart-style-grid" id="'+ product[i].id +'" ></a>';
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
        data:{sort:id},
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
                li += '<a class="add-cart-style-list" id="'+ product[i].id +'" ></a>';
                li += '<p class="style-price-list" ><strong>' + product[i].price + '</strong> руб.</p>';
                li+= '<div class="style-text-list" >' + product[i].description + '</div>';
                li += '</li>';
            }
            $('#product-list').html(li + '</ul>');
        }
    });
}
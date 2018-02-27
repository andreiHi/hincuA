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
    function header() {
        $('.top-auth').click(
            function () {
                if($('#block-top-auth').css('display') == 'none'){
                    $('.top-auth').attr('id','activ-button');
                    $('#block-top-auth').fadeIn(200);
                } else {
                    $('#block-top-auth').fadeOut(200);
                    $('.top-auth').attr('id','');
                }
            });
        $('#button-pass-show-hide').click(function () {
            var statuspass = $('#button-pass-show-hide').attr('class');
            if (statuspass=='pass-show'){
                $('#button-pass-show-hide').attr('class','pass-hide');
                var $input = $('#auth_pass');
                var change = "text";
                var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                    .attr("id", $input.attr("id"))
                    .attr("name", $input.attr("name"))
                    .attr('class', $input.attr('class'))
                    .val($input.val())
                    .insertBefore($input);
                $input.remove();
                $input = rep;
            }else {
                $('#button-pass-show-hide').attr('class','pass-show');
                var $input = $("#auth_pass");
                var change = "password";
                var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                    .attr("id", $input.attr("id"))
                    .attr("name", $input.attr("name"))
                    .attr('class', $input.attr('class'))
                    .val($input.val())
                    .insertBefore($input);
                $input.remove();
                $input = rep;
            }
        });
        $("#button-auth").click(function() {
            var auth_login = $("#auth_login").val();
            var auth_pass = $("#auth_pass").val();
            var send_login,send_pass,auth_rememberme;
            if (auth_login == "" || auth_login.length > 30 ) {
                $("#auth_login").css("borderColor","#FDB6B6");
                send_login = 'no';
            }else {
                $("#auth_login").css("borderColor","#DBDBDB");
                send_login = 'yes';
            }
            if (auth_pass == "" || auth_pass.length > 15 ) {
                $("#auth_pass").css("borderColor","#FDB6B6");
                send_pass = 'no';
            }else { $("#auth_pass").css("borderColor","#DBDBDB");
                send_pass = 'yes'; }

            if ($("#rememberme").prop('checked')) {
                auth_rememberme = 'yes';

            }else {
                auth_rememberme = 'no';
            }

            if ( send_login == 'yes' && send_pass == 'yes' ) {
                $("#button-auth").hide();
                $(".auth-loading").show();

                $.ajax({
                    async: true,
                    method: "POST",
                    url: "/shop/authorization",
                    data:{login:auth_login, password:auth_pass, remember:auth_rememberme},
                    cache: false,
                    complete: function(data) {
                        var res = JSON.parse(data.responseText);
                        if (res === true) {
                            location.reload();
                        }else {
                            $("#message-auth").slideDown(400);
                            $(".auth-loading").hide();
                            $("#button-auth").show();
                        }
                    }
                });
            }
        });

    }
}
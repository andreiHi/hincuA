$(document).ready(function () {
     $('#block-header').load("assets/include/block-header.html");
     $('#block-category').load("assets/include/block-category.html");
     $('#block-parameter').load("assets/include/block-parameter.html");
     // $('#block-news').load("assets/include/block-news.html");
     $('#block-footer').load("assets/include/block-footer.html");
    showproducts();
     // $('#newsticker').jCarouselLite({
     //     vertical:true,
     //     hoverPause:true,
     //     btnPrev:"#news-prev",
     //     btnNext:"#news-next",
     //     visible:3,
     //     auto:3000,
     //     speed:500
     // });
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
    //showproducts();
});
function showproducts() {
        $.ajax({
            method:"GET",
            url:"/shop/products",
            data:{product:"all"},
            complete:function (data) {
                var product = JSON.parse(data.responseText);
                var li ="";
                for (var i =0; i<product.length; i++ ) {
                    li+='<li><p class="style-title-grid"><a href="http:/shop/mobile/' + product[i].id + '"/>';
                        li+= product[i].name + '</a></p>';

                    //<p class="style-title-grid" ><a href="http://shop//mobile/'.$row["products_id"].'-'.ftranslite($row["title"]).'/" >'.$row["title"].'</a></p>
                    li+= '<ul class="reviews-and-counts-grid">';
                    // li+='<li><img src="assets/imj/eye-icon.png" /><p>'+'show'+'</p></li>';
                    // li+='<li><img src="assets/imj/comment-icon.png" /><p>'+show+'</p></li>';
                    li+= '</ul>';
                    // <a class="add-cart-style-grid" tid="'.$row["products_id"].'" ></a>
                    // <p class="style-price-grid" ><strong>'.group_numerals($row["price"]).'</strong> ���.</p>
                    // <div class="mini-features" >
                    //     '.$row["mini_features"].'
                    //     </div>
                    li+='</li>';
                    //



                }

                $('#block-tovar-grid').html(li);
            }



        });
}
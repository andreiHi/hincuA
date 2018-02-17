$(document).ready(function () {
     $('#block-header').load("assets/include/block-header.html");
     $('#block-category').load("assets/include/block-category.html");
     $('#block-parameter').load("assets/include/block-parameter.html");
     // $('#block-news').load("assets/include/block-news.html");
     $('#block-footer').load("assets/include/block-footer.html");

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
            btnNext: "#news-next",
            btnPrev: "#news-prev",
            auto:3000,
            speed:500
        });
    });
});
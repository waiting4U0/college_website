// JavaScript Document
var main = {};

main.homepageGoTo = function (url1, url2, url3, param) {
    $.post(url1, param, function (data) {
        $('#main-page-content1').html(data);

        document.body.scrollTop = 0;
    });
    $.post(url2, param, function (data) {
        $('#main-page-content2').html(data);

        document.body.scrollTop = 0;
    });
    $.post(url3, param, function (data) {
        $('#main-page-content3').html(data);

        document.body.scrollTop = 0;
    });
};

main.menuGoTo = function (url, param) {
    $.post(url, param, function (data) {
        $('#main-page-content1').html(data);
        $('#main-page-content2').empty();
        $('#main-page-content3').empty();

        document.body.scrollTop = 0;
    });
};


$(function() {

    $.post("/news/search",function (data) {
        if (data.code == 200) {
            var availableTags = data.data.toString().split(",");
            $( "#tags" ).autocomplete({
                source: availableTags
            });
        }
    },"json");

});

function goto(url) {
    $.post(url, "", function (data) {
        $('.major_introduce').html(data);

        document.body.scrollTop = 0;
    });
}
/**
 * Created by cdshenjian on 2016/11/4.
 */
var skuJS = {};
var init_pageSize = 100;

skuJS.search = function() {
    var requestData={
        startIndex:1,
        pageSize:init_pageSize,
        s_sku:$("#s_sku").val(),
        s_start_date:$("#s_start_date").val(),
        s_end_date:$("#s_end_date").val()
    };
    main.menuGoTo("/sku/list",requestData);
};

skuJS.exportAsExcel = function() {
    $("#form_export").submit();
};


skuJS.exportPin = function(request_url)
{
    var form = $("<form>");
    form.attr('method','post');
    form.attr('action',request_url);

    var input3 = $('<input>');
    input3.attr('type','hidden');
    input3.attr('name','s_start_date');
    input3.attr('value',$("#s_start_date").val());

    var input4 = $('<input>');
    input4.attr('type','hidden');
    input4.attr('name','s_end_date');
    input4.attr('value',$("#s_end_date").val());

    form.append(input3);
    form.append(input4);
    $("body").append(form);
    form.submit();
};
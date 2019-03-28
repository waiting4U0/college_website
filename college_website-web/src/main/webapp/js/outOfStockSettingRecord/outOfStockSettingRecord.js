/**
 * outOfStockSettingRecordJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-08-24
 */
var outOfStockSettingRecordJS = {};

outOfStockSettingRecordJS.search = function () {
    var requestData = {
        s_padSn: $("#s_padSn").val(),
        s_pushSwitch: $("#s_pushSwitch").val()
    };
    main.menuGoTo("/outOfStockSettingRecord/list", requestData);
};

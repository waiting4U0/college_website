var dateCompJS = {};

dateCompJS.checkDateRange = function (dstart, dend, fieldName, maxDayGap) {

    if (dstart != "" && dend != "") {
        var a = dateCompJS.getDateInt(dend) - dateCompJS.getDateInt(dstart) + 1;
        if (maxDayGap == "") maxDayGap = 31;
        //alert(a);
        if (a < 0) {
            alert(fieldName + "的结束时间要大于开始时间!");
            return false;
        } else {
            if (a > new Number(maxDayGap)) {
                alert(fieldName + "的间隔最大时间为" + maxDayGap + "天内!");
                return false;
            } else return true;

        }
    }
    return true;

};

dateCompJS.getDateInt = function (dateStr) {
    var date = new Date(dateStr);
    return new Number(date.getTime() / (24 * 60 * 60 * 1000));
};

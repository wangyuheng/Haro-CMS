function showBirthday(val) {
    var cType = $("#certificateType").val();
    if (cType != 1) {
        return;
    }
    var birthdayValue;
    if (15 == val.length) { // 15位身份证号码
        birthdayValue = val.charAt(6) + val.charAt(7);
        if (parseInt(birthdayValue) < 10) {
            birthdayValue = '20' + birthdayValue;
        } else {
            birthdayValue = '19' + birthdayValue;
        }
        birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-'
                + val.charAt(10) + val.charAt(11);
        if (parseInt(val.charAt(14) / 2) * 2 != val.charAt(14))
            $('[name="userInfo.gender"][value="1"]')[0].checked = true;
        else
            $('[name="userInfo.gender"][value="2"]')[0].checked = true;
        $("#birthday").attr("value", birthdayValue);
    }
    if (18 == val.length) { // 18位身份证号码
        birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9)
                + '-' + val.charAt(10) + val.charAt(11)

                + '-' + val.charAt(12) + val.charAt(13);
        if (parseInt(val.charAt(16) / 2) * 2 != val.charAt(16))

            $('[name="userInfo.gender"][value="1"]')[0].checked = true;
        else
            $('[name="userInfo.gender"][value="2"]')[0].checked = true;
        if (val.charAt(17) != IDCard(val)) {
            $("#certificateNo").css("backgroundColor", '#ffc8c8');
        } else {
            $("#certificateNo").css("backgroundColor", 'white');
        }
        $("#birthday").attr("value", birthdayValue);
    }
}

// 18位身份证号最后一位校验
function IDCard(Num) {
    if (Num.length != 18)
        return false;
    var x = 0;
    var y = '';

    for (i = 18; i >= 2; i--)
        x = x + (square(2, (i - 1)) % 11) * parseInt(Num.charAt(19 - i - 1));
    x %= 11;
    y = 12 - x;
    if (x == 0)
        y = '1';
    if (x == 1)
        y = '0';
    if (x == 2)
        y = 'X';
    return y;
}

// 求得x的y次方
function square(x, y) {
    var i = 1;
    for (j = 1; j <= y; j++)
        i *= x;
    return i;
}
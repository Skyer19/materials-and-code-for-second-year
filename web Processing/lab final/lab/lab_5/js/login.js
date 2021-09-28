/*
用户名
失去焦点时验证是否符合规则
参数UName：用户输入的用户名
参数eId：错误提示div的id
返回值：若符合要求：返回true，不符合：返回false；
*/
function verifyUName(UName, eId) {
    var msg = "";
    var strUserName = UName.trim();
    //用户名必须6~20位.
    if (strUserName.length < 6 || strUserName.length > 20) {
        msg = "<font color='red'>用户名必须6~20位</font>";
        showErrorMsg(eId, msg);
        return false;
    }
    //使用正则表达式验证
    var pattern = /^[a-zA-Z]{1}[a-zA-Z0-9_]{5,19}$/
    if (pattern.test(strUserName)) {
        clearMsg(eId);
        return true;
    } else {
        msg = "<font color='red'>用户名输入错误</font>";
        showErrorMsg(eId, msg);
        return false;
    }
}

function verifyPass(pwd, eId) {
    var msg = "";
    var strPwd = pwd.trim();
    //密码必须6~20位.
    if (strPwd.length < 6 || strPwd.length > 20) {
        msg = "<font color='red'>密码必须6~20位</font>";
        showErrorMsg(eId, msg);
        return false;
    }
    //使用正则表达式验证
    var pattern = /^[a-zA-Z0-9]{0,19}$/;
    if (pattern.test(strPwd)) {
        clearMsg(eId);
        return true;
    } else {
        msg = "<font color='red'>密码格式不错误</font>";
        showErrorMsg(eId, msg);
        return false;
    }
}

function verifyRePass(pwd, pwd1, eId) {
    var msg = "";
    var strPwd1 = pwd.trim();
    var strPwd2 = pwd1.trim();
    //密码必须6~20位.
    if (strPwd1 != strPwd2) {
        msg = "<font color='red'>两次密码输入不一致</font>";
        showErrorMsg(eId, msg);
        return false;
    } else {
        clearMsg(eId);
        return true;
    }
}

function verifyChecked(checkName, eId) {
    var checkName = document.getElementsByName(checkName);
    if (checkName.checked) {
        return true;
    }
    msg = "<font color='red'>请选择</font>";
    showErrorMsg(eId, msg);
    return false;
}
        function verifyInput() {
            //通过表单名称，得到输入表单
            var form = document.form_register;
            //以此验证
            if (verifyUName(form.txt_Uname.value, 'div_uname') &&
                verifyPass(txt_Pwd1.value, 'div_pwd1') &&
                verifyRePass(txt_Pwd1.value, txt_Pwd2.value, 'div_pwd2') &&
                verifyChecked('check_name', 'div_check')) {
                alert("恭喜，注册成功");
                form.submit;
                return (true);
            } else {
                alert("注册失败，请安红色提示修改");
                return (false);
            }
        }
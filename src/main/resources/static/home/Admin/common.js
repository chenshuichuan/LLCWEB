/**
 * Created by BlueFisher on 2016/10/30 0030.
 */

/****************************************日历插件*******************************************/
function calendar() {
    $("#dtBox").DateTimePicker(
        {
            dateFormat: "yyyy-MM-dd",
            dateTimeFormat: "yyyy-MM-dd HH:mm:ss",
            timeFormat: "HH:mm",
            shortDayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            fullDayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            shortMonthNames: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
            fullMonthNames: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
            titleContentDate: "设置日期",
            titleContentTime: "设置时间",
            titleContentDateTime: "设置日期和时间",
            buttonsToDisplay: ["HeaderCloseButton", "SetButton", "ClearButton"],
            setButtonContent: "确定",
            clearButtonContent: "取消"
        });
}

/****************************************高级搜索页面弹出与收起*******************************************/
function adSearch() {
    $('.Ad_search a').click(function () {
        $('.theme-popover-mask').fadeIn(100);
        $('.theme-popover').slideDown(200);
    });
    $('.remove').click(function () {
        $('.theme-popover-mask').fadeOut(100);
        $('.theme-popover').slideUp(200);
    });
}

/********************************json 的时间格式进行拼接***************************/
function converDate(date) {
    var year = new Date(date).getFullYear();
    var month = "0" +(new Date(date).getMonth() + 1);
    var day ="0" + new Date(date).getDate();
    return year + "-" + month.substring(month.length - 2) + "-" + day.substring(day.length - 2) ;
}

/********************************时间格式转换为Json的时间格式***************************/
function convertDateToJsonDate(strDate) {
    var splitted = strDate.split("-");
    var newDate = new Date(Date.UTC(splitted[0], splitted[1] - 1, splitted[2]));
    return newDate.getTime();
}

/****************************************填充编辑页面子方法*******************************************/
function fill(type, idValue) {
    var name = $("tbody.tableContent tr").eq(idValue).find('#' + type).text();
    $(".input_" + type).val(name);
    getInput(type);
}

/****************************************动态获取input的value值*******************************************/
function getInput(type) {
    $(".input_" + type).bind('input propertychange', function () {
        $(".input_" + type).val();
    });
}

/****************************************清除编辑页面子方法*******************************************/
function clear() {
    $("input").val("");
}

/****************************************取消事件*******************************************/
function cancel() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
}

/****************************************删除事件*******************************************/
function del(obj,type) {
    var idValue = $(obj).attr("id");
    var idName = $("tbody.tableContent tr").eq(idValue).find('#id').text();

    $.getJSON('../admin/delete'+type+'?id=' + idName, function (obj) {
        if (obj.errorCode != "0x03") {
            var idName = $("tbody.tableContent tr").eq(idValue).remove();
            // window.location.reload();           //成功后刷新页面
            alert("删除成功！！！");
        }
        else
            alert("无权访问！！！");
    });
}

/****************************************上传图片*******************************************/
function upLoad() {
    var fd = new FormData();
    var values = $(".upload>input[name='name']").val();
    fd.append("pic", $("#upLoadFile").get(0).files[0]);
    fd.append("name", 'values');
    $.ajax({
        url: '/upload',
        type: 'POST',
        processData: false,
        contentType: false,
        //dataType:'JSON',
        data: fd,
        success: function (obj) {
            if(obj.status){
                // alert(obj.data);
                // window.location.reload();           //成功后刷新页面
                alert("上传成功！！！！");
                $(".input_pic_code").val(obj.data);
                $('#preview img').attr('src', '../getPic?id=' + obj.data);
            }
            else{
                alert("图片已经存在！！！！");
            }
            //window.location.reload();           //成功后刷新页面
        }
    });
}

/****************************************动态获取图片的value值*******************************************/
function getPicInput() {
    $(".upload>input[name='name']").attr('value', getName());
    $(".upload>input[name='name']").val(getName());
    $(".upload>input[name='name']").bind('input propertychange', function () {
        $(".upload>input[name='name']").attr("value", $(this).val());
    });
}
/****************************************获取图片的名字*******************************************/
function getName() {
    var file = $("#upLoadFile").val();
    var fileName = getFileName(file);
    //alert(file);          //********.jpg

    function getFileName(o) {
        var pos = o.lastIndexOf('\\');
        var pos1 = o.lastIndexOf('.');
        return o.substring(pos + 1, pos1);
    }

    return fileName;
}

/****************************************通过回车触发的更换图片事件*******************************************/
function picPreviewByEnter(event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;       //处理兼容性问题
    $("input.input_pic_code").bind('input propertychange', function () {
        $("input.input_pic_code").attr("value", $(this).val());
    });
    if (keyCode == "13") {
        var picCode = $("input.input_pic_code").val();
        $('#preview img').attr('src', '../getPic?id=' + picCode);
    }
}

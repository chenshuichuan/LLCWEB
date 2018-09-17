/**
 * Created by BlueFisher on 2016/10/30 0030.
 */
$(document).ready(function () {
    getConferenceByPage(1,15,conJSON);
    /****************************************分页*******************************************/
    $("#demo4").paginate({
        count: page(),      //totalPage
        start: 1,
        display: 10,
        border: false,
        text_color: '#1ABC9C',
        background_color: 'none',
        text_hover_color: '#107863',
        background_hover_color: 'none',
        images: false,
        mouse: 'press',
        onChange: function (page) {
            getConPage(page);
            $('.paperTable').addClass('tableContent');
            $('.searchTable').removeClass('tableContent');
        }
    });

    /****************************************日历插件*******************************************/
    calendar();

    /****************************************高级搜索页面弹出与收起*******************************************/
    adSearch();

});

/****************************************填充论文表单*******************************************/
function conJSON(obj) {
    if (obj.status) {
        $("tbody.paperTable>tr").remove();
        fillConTable(obj, "paper");
    }
}

/****************************************填充表格方法*******************************************/
function fillConTable(obj, type) {
    $.each(obj.data.resultList, function (i, item) {
        $("tbody." + type + "Table").append("<tr id=" + i + ">");
        var date = new Date(item.time).toLocaleDateString();
        var d = converDate(date);
        $("tbody." + type + "Table tr").eq(i).append("<td id='id'>" + item.id + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='pic_code' >" + item.picCode + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='title' class='ellipsis'>" + item.title + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='menber' class='ellipsis'>" + item.menber + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='time'>" + d + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='details' class='ellipsis'>" + item.details + "</td>");
        //$("tbody.paperTable tr").eq(i).append("<td id='paper_url'>" + item.paper_url + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='ope" + i + "'>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#'class='edit' onclick='edit(this)' id='" + i + "'></a>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#' class='del' onclick='firmDel(this)' id='" + i + "'></a>");
    });
}

/****************************************页面按键事件*******************************************/
function getConPage(page) {
    getConferenceByPage(page, 15, conJSON);
}

/****************************************获取总共的页数*******************************************/
function page() {
    var totalPage;
    $.ajax({
        url: '../limitConference?currentPage=1&pageSize=15',
        async: false,
        dataType: 'json',
        success: function (obj) {
            totalPage = obj.data.totalPage;
        }
    });
    return totalPage;
}

/****************************************填充编辑页面*******************************************/
function edit(obj) {
    $(".content").addClass("disappear");
    $(".content_edit").removeClass("appear");
    $(".save,#first").removeClass("disappear");
    $(".submit").addClass("appear");
    var idValue = $(obj).attr("id");
    fill("id", idValue);
    fill("pic_code",idValue);
    fill("title", idValue);
    fill("menber", idValue);
    fill("time", idValue);
    // document.getElementById('details').innerText("sssssss");
    fill("details", idValue);

    var picCode = $("tbody.tableContent tr").eq(idValue).find('#pic_code').text();
    $('#preview img').attr('src','../getPic?id='+picCode);
}

/****************************************确认是否删除事件*******************************************/
function firmDel(obj) {
    //利用对话框返回的值 （true 或者 false）
    if (confirm("你确定删除吗？")) {
        del(obj,'Conference');
    }
    else {
        // alert("点击了取消");
    }
}

/****************************************封装数组对象为JSON*******************************************/
function genJson() {
    var obj = new Object();

    var strDate = $(".input_time").attr('value');
    var d = convertDateToJsonDate(strDate);

    obj.id = $(".input_id").val();
    obj.picCode = $(".input_pic_code").val();
    obj.title = $(".input_title").val();
    obj.menber = $(".input_menber").val();
    obj.time = d;
    obj.details = $(".input_details").val();

    var postdata = JSON.stringify(obj);
    return postdata;

}

/****************************************保存事件*******************************************/
function save() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    //$(".search_text input").attr("value", genJson());
    sentJSON('modifyConference');
}

/****************************************提交事件*******************************************/
function submit() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    sentJSON("addConference");
}

/****************************************发送JSON到后台*******************************************/
function sentJSON(type) {
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/admin/' + type,
        data: genJson(),
        success: function (obj) {
            if (obj.errorCode != "0x03") {
                // window.location.reload();           //成功后刷新页面
                alert('操作成功！！！');
                getConferenceByPage(1,15,conJSON);
            }
            else
                alert('无权访问！！！');
        }
    });
}

/****************************************添加事件*******************************************/
function add() {
    $(".content,#first").addClass("disappear");
    $(".content_edit").removeClass("appear");
    $(".save").addClass("disappear");
    $(".submit").removeClass("appear");

    clear();

    getInput("id");
    getInput("pic_code");
    getInput("team_num");
    getInput("title");
    getInput("author");
    getInput("publish_time");
    getInput("paper_url");
}

/****************************************通过回车触发的搜索事件*******************************************/
function searchByEnter(event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;       //处理兼容性问题
    $("input[name='search']").bind('input propertychange', function () {
        $("input[name='search']").attr("value", $(this).val());
    });
    if (keyCode == "13") {
        searchFill();
    }
}

/****************************************通过鼠标触发的搜索事件*******************************************/
function searchByClick() {
    $("input[name='search']").bind('input propertychange', function () {
        $("input[name='search']").attr("value", $(this).val());
    });
    searchFill();
}

/****************************************搜索事件的填充方法*******************************************/
function searchFill() {
    $('.searchTable').html("");
    var obj = new Object();
    obj.title = $("input[name='search']").val();
    var postdata = JSON.stringify(obj);
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '../limitPapers?currentPage=1&pageSize=100',
        data: postdata,
        success: function (obj) {
            $('.paperTable').addClass('disappear').removeClass('tableContent');
            $('.searchTable').removeClass('appear').addClass('tableContent');
            fillPaperTable(obj, "search");
        }
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
                alert(obj.data);
                // window.location.reload();           //成功后刷新页面
                alert("上传成功！！！！");
                $(".input_pic_code").val(obj.data);
            }
            else{
                alert("图片已经存在！！！！");
            }
            //window.location.reload();           //成功后刷新页面
        }
    });
}


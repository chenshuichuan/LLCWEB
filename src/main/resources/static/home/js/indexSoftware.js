/**
 * Created by BlueFisher on 2016/12/16 0016.
 */
$(document).ready(function () {
    var software_url = "../limitSoftWares?currentPage=1&pageSize=15";
    gainJSON(software_url, softwareJSON);


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
            getSoftwarePage(page);
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
function softwareJSON(obj) {
    if (obj.status) {
        $("tbody.paperTable>tr").remove();
        fillPaperTable(obj, "paper");
    }
}

/****************************************填充表格方法*******************************************/
function fillPaperTable(obj, type) {
    $.each(obj.data.resultList, function (i, item) {
        $("tbody." + type + "Table").append("<tr id=" + i + ">");
        var date = new Date(item.publishTime).toLocaleDateString();
        var d = converDate(date);
        $("tbody." + type + "Table tr").eq(i).append("<td id='id'>" + item.id + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='project_num'>" + item.projectNum + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='number'>" + item.number + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='title' class='ellipsis'>" + item.title + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='author'>" + item.author + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='publish_time'>" + d + "</td>");
        //$("tbody.paperTable tr").eq(i).append("<td id='paper_url'>" + item.paper_url + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='ope" + i + "'>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#'class='edit' onclick='edit(this)' id='" + i + "'></a>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#' class='del' onclick='firmDel(this)' id='" + i + "'></a>");
    });
}

/****************************************页面按键事件*******************************************/
function getSoftwarePage(page) {
    var paper_url = "../limitSoftWares?currentPage=" + page + "&pageSize=15";
    getSoftwareByPage(page, 15, softwareJSON);
}

/****************************************获取总共的页数*******************************************/
function page() {
    var totalPage;
    $.ajax({
        url: '../limitSoftWares?currentPage=1&pageSize=15',
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
    fill("project_num", idValue);
    fill("number", idValue);
    fill("title", idValue);
    fill("author", idValue);
    fill("publish_time", idValue);
}

/****************************************确认是否删除事件*******************************************/
function firmDel(obj) {
    //利用对话框返回的值 （true 或者 false）
    if (confirm("你确定删除吗？")) {
        del(obj,'Software');
    }
    else {

    }
}

/****************************************封装数组对象为JSON*******************************************/
function genJson() {
    var obj = new Object();

    var strDate = $(".input_publish_time").attr('value');
    var d = convertDateToJsonDate(strDate);

    obj.id = $(".input_id").val();
    obj.projectNum = $(".input_project_num").val();
    obj.number = $(".input_number").val();
    obj.title = $(".input_title").val();
    obj.author = $(".input_author").val();
    obj.publishTime = d;

    var postdata = JSON.stringify(obj);
    return postdata;

}

/****************************************保存事件*******************************************/
function save() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    //$(".search_text input").attr("value", genJson());
    sentJSON('modifySoftware');
}

/****************************************提交事件*******************************************/
function submit() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    sentJSON("addSoftware");
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
                var software_url = "../limitSoftWares?currentPage=1&pageSize=15";
                gainJSON(software_url, softwareJSON);
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
    getInput("project_num");
    getInput("number");
    getInput("title");
    getInput("author");
    getInput("publish_time");
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
        url: '../limitSoftWares?currentPage=1&pageSize=100',
        data: postdata,
        success: function (obj) {
            $('.paperTable').addClass('disappear').removeClass('tableContent');
            $('.searchTable').removeClass('appear').addClass('tableContent');
            fillPaperTable(obj, "search");
        }
    });
}

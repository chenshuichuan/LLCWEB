/**
 * Created by BlueFisher on 2016/10/30 0030.
 */
$(document).ready(function () {
    var teamNumUrl = window.location.search;
    var teamNum = teamNumUrl.substring(teamNumUrl.lastIndexOf('=') + 1, teamNumUrl.length);

    getProjectByPage(1, 15, teamNum, projectJSON);
    //alert(teamNum);
    $("#upLoadFile").val("");
    $(".upload>input").val("");

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
            getPorjectPage(page);
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
function projectJSON(obj) {
    if (obj.status) {
        $("tbody.paperTable>tr").remove();
        fillProjectTable(obj, "paper");
    }
}

/****************************************填充表格方法*******************************************/
function fillProjectTable(obj, type) {
    $.each(obj.data.resultList, function (i, item) {
        $("tbody." + type + "Table").append("<tr id=" + i + ">");
        var s_time = new Date(item.startTime).toLocaleDateString();
        var s_d = converDate(s_time);
        var e_time = new Date(item.endTime).toLocaleDateString();
        var e_d = converDate(e_time);
        $("tbody." + type + "Table tr").eq(i).append("<td id='id'>" + item.id + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='team_num'>" + item.teamNum + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='pic_code' >" + item.picCode + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='title'  class='ellipsis'>" + item.projectName + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='start_time'>" + s_d + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='end_time'>" + e_d + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='task' class='ellipsis'>" + item.task + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='unfinished' class='ellipsis'>" + item.unfinished + "</td>");
        //$("tbody.paperTable tr").eq(i).append("<td id='paper_url'>" + item.paper_url + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='ope" + i + "'>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#'class='edit' onclick='edit(this)' id='" + i + "'></a>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#' class='del' onclick='firmDel(this)' id='" + i + "'></a>");
    });
}

/****************************************页面按键事件*******************************************/
function getPorjectPage(page) {
    var teamNumUrl = window.location.search;
    var teamNum = teamNumUrl.substring(teamNumUrl.lastIndexOf('=') + 1, teamNumUrl.length);
    getProjectByPage(page, 15, teamNum, projectJSON);
}

/****************************************获取总共的页数*******************************************/
function page() {
    var teamNumUrl = window.location.search;
    var teamNum = teamNumUrl.substring(teamNumUrl.lastIndexOf('=') + 1, teamNumUrl.length);
    var totalPage;
    $.ajax({
        url: '../limitTeamProject?currentPage=1&pageSize=15&teamNum=' + teamNum,
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
    fill("team_num", idValue);
    fill("pic_code", idValue);
    fill("title", idValue);
    fill("start_time", idValue);
    fill("end_time", idValue);
    fill("task", idValue);
    fill("unfinished", idValue);

    var picCode = $("tbody.tableContent tr").eq(idValue).find('#pic_code').text();
    $('#preview img').attr('src', '../getPic?id=' + picCode);
}

/****************************************确认是否删除事件*******************************************/
function firmDel(obj) {
    //利用对话框返回的值 （true 或者 false）
    if (confirm("你确定删除吗？")) {
        del(obj, 'Project');
    }
    else {
        // alert("点击了取消");
    }
}
/****************************************封装数组对象为JSON*******************************************/
function genJson() {
    var obj = new Object();

    var s_Date = $(".input_start_time").val();
    var s_d = convertDateToJsonDate(s_Date);
    var e_Date = $(".input_end_time").val();
    var e_d = convertDateToJsonDate(e_Date);

    obj.id = $(".input_id").val();
    obj.teamNum = $(".input_team_num").val();
    obj.picCode = $(".input_pic_code").val();
    obj.projectName = $(".input_title").val();
    obj.startTime = s_d;
    obj.endTime = e_d;
    obj.task = $(".input_task").val();
    obj.unfinished = $(".input_unfinished").val();

    var postdata = JSON.stringify(obj);
    return postdata;

}

/****************************************保存事件*******************************************/
function save() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    //$(".search_text input").attr("value", genJson());
    sentJSON('modifyProject');
}

/****************************************提交事件*******************************************/
function submit() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    sentJSON("addProject");
}

/****************************************发送JSON到后台*******************************************/
function sentJSON(type) {
    var teamNumUrl = window.location.search;
    var teamNum = teamNumUrl.substring(teamNumUrl.lastIndexOf('=') + 1, teamNumUrl.length);
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/admin/' + type,
        data: genJson(),
        success: function (obj) {
            if (obj.errorCode != "0x03") {
                // window.location.reload();           //成功后刷新页面
                alert('操作成功！！！');
                var project_url = "../limitTeamProject?currentPage=1&pageSize=15&teamNum=" + teamNum;
                gainJSON(project_url, projectJSON);
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
    getInput("team_num");
    getInput("pic_code");
    getInput("title");
    getInput("start_time");
    getInput("end_time");
    getInput("task");
    getInput("unfinished");
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
            fillProjectTable(obj, "search");
        }
    });
}

/****************************************判断项目名称方法*******************************************/
function teamNum() {
    var teamCode = $('#team').find('option:selected').attr('teamNum');
    window.location.href = "indexProject.html?teamNum=" + teamCode;
}

/****************************************动态获取input的value值*******************************************/
function getInput() {
    $(".tab>input[name='pic']").bind('input propertychange', function () {
        $(".tab>input[name='pic']").attr("value", $(this).val());
    });
}

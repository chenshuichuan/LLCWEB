/*
* 作者：ricardo
* 描述：论文管理页面js
* 编写结构说明：
*     接口URL-->
*     页面加载-->
*     事件监听-->
*
**/
//获取论文分页数据
var urlGetPage="/paper/page";
//获取paper对应的详细的信息
var urlGetPaperById="";
//添加
var urlAdd="";
//更改
var urlSave="/paper/save";
//删除
var urlDelete="/paper/delete";
var urlGetFileById="/file/getFileById";

//加载遮罩
var $wrapper = $('#paper-table');

$(document).ready(function () {

    //paper-table
    var $paperTable = $('#paper-table');
    var  _table = $paperTable.dataTable(
        $.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION,
            {
                ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                    //手动控制遮罩
                    $wrapper.spinModal();
                    //封装请求参数
                    var param = usersManage.getQueryCondition(data);
                    $.ajax({
                        type: "GET",
                        url: urlGetPage,
                        cache: false,	//禁用缓存
                        data: param,	//传入已封装的参数
                        dataType: "json",
                        success: function (result) {
                            //异常判断与处理
                            if (result.errorCode) {
                                $.dialog.alert("查询失败。错误码：" + result.errorCode);
                                return;
                            }
                            //封装返回数据，这里仅演示了修改属性名
                            var returnData = {};
                            returnData.draw = result.draw;
                            returnData.recordsTotal = result.total;
                            returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                            returnData.data = result.pageData;
                            //关闭遮罩
                            $wrapper.spinModal(false);
                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                            callback(returnData);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.dialog.alert("查询失败");
                            $wrapper.spinModal(false);
                        }
                    });
                },
                columns: [
                    {
                        data: "title"
                    },
                    {
                        data: "date",
                        width: "80px"
                    },
                    {
                        data: "authorList",
                        width: "250px",
                        className: "ellipsis",
                        render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS
                    },
                    {
                        data: null,
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return "公开";
                        }
                    },
                    {
                        data: "date",
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return dateToString(data);
                        }
                    },
                    {
                        className: "td-operation",
                        data: null,
                        width: "120px",
                        defaultContent: "",
                        orderable: false
                    }
                ],
                "createdRow": function (row, data, index) {

                    //不使用render，改用jquery文档操作呈现单元格
                    var $btnEdit = $(' <button class="btn btn-warning btn-edit" type="button" data-toggle="tooltip"data-placement="bottom" title="编辑"> <i class="fa fa-edit"></i> </button>');
                    var $btnDelete = $('<button class="btn btn-danger  btn-delete" type="button" data-toggle="tooltip"data-placement="bottom" title="删除"> <i class="fa fa-trash-o"></i> </button>');

                    $('td', row).eq(5).append($btnEdit).append($btnDelete);
                },
                "drawCallback": function (settings) {
                    //默认选中第一行
                    $("tbody tr", $paperTable).eq(0).click();
                }
            })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    //行点击事件
    $("tbody", $paperTable).on("click", "tr", function (event) {
        $(this).addClass("active").siblings().removeClass("active");//有空再试试
        //获取该行对应的数据
        var item = _table.row($(this).closest('tr')).data();
        usersManage.currentItem = item;
        usersManage.showUser(item);
        $("#view-panel").show().siblings(".panel").hide();
    });
    $paperTable.on("click", ".btn-edit", function () {
        //编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        //切换panel
        $("#edit-panel").show().siblings(".panel").hide();
        usersManage.editUser(item);
    }).on("click", ".btn-delete", function () {
        //删除按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        usersManage.deleteUser(item);
        _table.draw();
    });


    $("#btn-advanced-search").click(function () {
        _table.draw();
    });

    $("#btn-add-user").click(function () {
        $("#add-panel").show().siblings(".panel").hide();
    });
    $("#btn-edit-bt").click(function () {
        $("#edit-panel").show().siblings(".panel").hide();
    });
    $("#btn-add-save").click(function () {
        $.dialog.tips("保存添加测试")
    });
    $("#btn-edit-save").click(function () {
        $.dialog.tips("保存编辑测试")
    });
    $("#btn-add-cancel").click(function () {
        $("#view-panel").show().siblings(".panel").hide();
    });
    $("#btn-edit-cancel").click(function () {
        $("#view-panel").show().siblings(".panel").hide();
    });
});


var usersManage = {
    currentItem: null,
    fuzzySearch: true,
    getQueryCondition: function (data) {
        var param = {};
        param.username = $("#search-name").val();
        //模糊查找标志，当有高级查找时，标记为false
        param.fuzzySearch = usersManage.fuzzySearch;
        param.fuzzy = $("#search-name").val();
        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.draw = data.draw;
        return param;
    },
    showUser: function (item) {
        $("#view-title").text(item.title);
        $("#view-abstract").text(item.introduction);
        $("#view-original_link").text(item.originalLink);
        $("#view-source_file").text((item.id==null||item.id==undefined)?"":(urlGetFileById+"?id="+item.sourceFile));
        $("#view-belong_project").text(item.belongProject);
        $("#view-periodical").text(item.periodical);
        //更多操作
    },
    editUser: function (item) {
        $("#edit-id").val(item.id);
        $("#edit-title").val(item.title);
        $("#edit-author").val(item.authorList);
        $("#edit-abstract").val(item.introduction);
        $("#edit-profile").val("公开");
        $("#edit-belong_project").val(item.belongProject);
        $("#edit-original_link").val(item.originalLink);
        $("#edit-source_file").val(item.sourceFile);
        $("#edit-periodical").val(item.periodical);
        $("#edit-state").val(item.state);
        $("#edit-date").val(dateToString(item.date));
    },
    editSaveUser: function (item) {
        var paper = {};
        paper.id = $("#edit-id").val();
        paper.title = $("#edit-title").val();
        paper.authorList = $("#edit-author").val();
        paper.introduction = $("#edit-abstract").val();
        paper.profile = $("#edit-profile").val();
        paper.belongProject = $("#edit-belong_project").val();
        paper.originalLink = $("#edit-original_link").val();
        paper.sourceFile = $("#edit-source_file").val();
        paper.sourceLink = $("#edit-source_file").val();
        paper.periodical = $("#edit-periodical").val();
        paper.state = $("#edit-state").val();
        paper.date = $("#edit-date").val();
        saveFun(urlSave,paper);
    },
    deleteUser: function (item) {
        var message = "确定删除论文:"+item.title+"?";
        deleteFun(message,urlDelete,item.id);
    }
};
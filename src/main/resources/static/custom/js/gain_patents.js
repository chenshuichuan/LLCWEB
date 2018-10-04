/*
* 作者：ricardo
* 描述：专利管理页面js
* 编写结构说明：
*     接口URL-->
*     页面加载-->
*     事件监听-->
*
**/
//获取专利分页数据
var urlGetPage="/patent/page";
//更改
var urlSave="/patent/save";
//删除
var urlDelete="/patent/delete";

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
                        data: "state",
                        width: "80px"
                    },
                    {
                        data: "authorList",
                        width: "150px",
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
                        data: "appliDate",
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
        $(this).addClass("active").siblings().removeClass("active");//有空再试试渲染选中行
        //$(this).addClass("active").siblings("tr").removeClass("active");
        // $("table tr").css('background-color','white');
        // $(this).css('background-color','blue');
        //获取该行对应的数据
        var item = _table.row($(this).closest('tr')).data();
        usersManage.currentItem = item;
        usersManage.showUser(item);
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
        $("#view-username").text(item.username);
        //更多操作
    },
    editUser: function (item) {
        $("#edit-id").val(item.id)
        $("#edit-username").val(item.username);
        $("#edit-password").val(item.password);
        //更多
    },
    editSaveUser: function (item) {

    },
    deleteUser: function (item) {

    }
};


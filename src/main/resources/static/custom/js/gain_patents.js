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
        usersManage.show(item);
    });
    $paperTable.on("click", ".btn-edit", function () {
        //编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        //切换panel
        $("#edit-panel").show().siblings(".panel").hide();
        usersManage.edit(item);
    }).on("click", ".btn-delete", function () {
        //删除按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        usersManage.delete(item);
    });


    $("#btn-advanced-search").click(function () {
        _table.draw();
    });

    $("#btn-add-user").click(function () {
        $("#add-title").val("");
        $("#add-author").val("");

        $("#add-abstract").val("");
        $("#add-profile").val(0);//

        $("#add-original_link").val("");
        $("#add-source_file").val("");
        $("#add-belong_project").val("");

        $("#add-appli_num").val("");
        $("#add-appli_date").val("");
        $("#add-public_num").val("");
        $("#add-public_date").val("");
        $("#add-agency").val("");
        $("#add-state").val("");
        $("#add-panel").show().siblings(".panel").hide();
    });
    $("#btn-edit-bt").click(function () {
        $("#edit-panel").show().siblings(".panel").hide();
    });
    $("#btn-add-save").click(function () {
        var patent={};
        patent.title=$("#add-title").val();
        patent.authorList=$("#add-author").val();
        patent.introduction=$("#add-abstract").val();
        patent.originalLink=$("#add-original_link").val();
        patent.sourceFile=$("#add-source_file").val();
        patent.belongProject= $("#add-belong_project").val();
        patent.profile=$("#add-profile").val();
        patent.appliNum=$("#add-appli_num").val();
        patent.appliDate=$("#add-appli_date").val();
        patent.publicNum=$("#add-public_num").val();
        patent.publicDate=$("#add-public_date").val();
        patent.agency=$("#add-agency").val();
        patent.state= $("#add-state").val();
        usersManage.save(patent);
        _table.draw();
    });
    $("#btn-edit-save").click(function () {
        var patent={};
        patent.id=$("#edit-id").val();
        patent.title=$("#edit-title").val();
        patent.authorList=$("#edit-author").val();
        patent.introduction=$("#edit-abstract").val();
        patent.originalLink=$("#edit-original_link").val();
        patent.sourceFile=$("#edit-source_file").val();
        patent.belongProject= $("#edit-belong_project").val();
        patent.profile=$("#edit-profile").val();
        patent.appliNum=$("#edit-appli_num").val();
        patent.appliDate=$("#edit-appli_date").val();
        patent.publicNum=$("#edit-public_num").val();
        patent.publicDate=$("#edit-public_date").val();
        patent.agency=$("#edit-agency").val();
        patent.state= $("#edit-state").val();
        usersManage.save(patent);
        _table.draw();
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
    show: function (item) {
        $("#view-title").text(item.title);
        $("#view-abstract").text(item.introduction);
        $("#view-original_link").text(item.originalLink);
        $("#view-source_file").text((item.id==null||item.id==undefined)?"":(urlGetFileById+"?id="+item.sourceFile));
        $("#view-belong_project").text(item.belongProject);

        $("#view-appli_num").text(item.appliNum);
        $("#view-public_num").text(item.publicNum);
        $("#view-public_date").text(dateToString(item.publicDate));
        $("#view-agency").text(item.agency);
        $("#view-state").text(item.state);
    },
    edit: function (item) {
        $("#edit-id").val(item.id);
        $("#edit-title").val(item.title);
        $("#edit-author").val(item.authorList);
        $("#edit-abstract").val(item.introduction);
        $("#edit-original_link").val(item.originalLink);
        $("#edit-source_file").val(item.sourceFile);
        $("#edit-belong_project").val(item.belongProject);
        $("#edit-profile").val(item.belongProject);
        $("#edit-appli_num").val(item.appliNum);
        $("#edit-appli_date").val(dateToString(item.appliDate));
        $("#edit-public_num").val(item.publicNum);
        $("#edit-public_date").val(dateToString(item.publicDate));
        $("#edit-agency").val(item.agency);
        $("#edit-state").val(item.state);
        //更多
    },
    save: function (patent) {
        saveFun(urlSave,patent)
    },
    delete: function (item) {
        var message ="确定删除专利：“"+item.title+"”的信息?";
        deleteFun(message,urlDelete,item.id);
    }
};


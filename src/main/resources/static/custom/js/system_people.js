/*
 * 作者：ricardo
 * 描述：专利管理页面js
 * 编写结构说明：
 *     接口URL-->
 *     页面加载-->
 *     事件监听-->
 *
 **/
//获取人员信息分页数据
var urlGetPage = "/people/page";
//获取document对应的内容信息
var urlGetDocumentById = "/document/getDocumentById";
//获取image对应的内容信息
var urlGetImageById = "/image/getImageById";
//更改
var urlSave = "/people/save";
//删除
var urlDelete = "/people/delete";
var urlGetFileById = "/file/getFileById";
//加载遮罩
var $wrapper = $('#people-table');

$(document).ready(function() {

    //people-table
    var $paperTable = $('#people-table');
    var _table = $paperTable.dataTable(
        $.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
            ajax: function(data, callback, settings) { //ajax配置为function,手动调用异步查询
                //手动控制遮罩
                $wrapper.spinModal();
                //封装请求参数
                var param = usersManage.getQueryCondition(data);
                $.ajax({
                    type: "GET",
                    url: urlGetPage,
                    cache: false, //禁用缓存
                    data: param, //传入已封装的参数
                    dataType: "json",
                    success: function(result) {
                        //异常判断与处理
                        if (result.errorCode) {
                            $.dialog.alert("查询失败。错误码：" + result.errorCode);
                            return;
                        }
                        //封装返回数据，这里仅演示了修改属性名
                        var returnData = {};
                        returnData.draw = result.draw;
                        returnData.recordsTotal = result.total;
                        returnData.recordsFiltered = result.total; //后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.pageData;
                        //关闭遮罩
                        $wrapper.spinModal(false);
                        callback(returnData);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        $.dialog.alert("查询失败");
                        $wrapper.spinModal(false);
                    }
                });
            },
            columns: [{
                    data: "id",
                    width: "80px"
                },
                {
                    data: "name",
                    width: "80px"
                },
                {
                    data: "phone",
                    width: "100px"
                },
                {
                    data: "email",
                    width: "100px"
                },
                {
                    data: "gender",
                    orderable: false, // 禁用排序
                    defaultContent: "",
                    width: "50px",
                    class: "gender_style", //给当前列添加样式
                    render: function(data, type, full, meta) {
                        if (data == "male") {
                            return data = '<i class="fa fa-male"></i>';
                        } else {
                            return data = '<i class="fa fa-female"></i>';
                        }
                    }
                },
                {
                    data: "position",
                    width: "80px"
                },
                {
                    className: "td-operation",
                    data: null,
                    width: "120px",
                    defaultContent: "",
                    orderable: false
                }
            ],
            "createdRow": function(row, data, index) {
                //不使用render，改用jquery文档操作呈现单元格
                var $btnEdit = $(' <button class="btn btn-warning btn-edit" type="button" data-toggle="tooltip"data-placement="bottom" title="编辑"> <i class="fa fa-edit"></i> </button>');
                var $btnDelete = $('<button class="btn btn-danger  btn-delete" type="button" data-toggle="tooltip"data-placement="bottom" title="删除"> <i class="fa fa-trash-o"></i> </button>');

                $('td', row).eq(7).append($btnEdit).append($btnDelete);
            },
            "drawCallback": function(settings) {
                //默认选中第一行
                $("tbody tr", $paperTable).eq(0).click();
            }
        })).api(); //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    //行点击事件
    $("tbody", $paperTable).on("click", "tr", function(event) {
        $(this).addClass("active").siblings().removeClass("active"); //有空再试试渲染选中行
        //$(this).addClass("active").siblings("tr").removeClass("active");
        // $("table tr").css('background-color','white');
        // $(this).css('background-color','blue');
        //获取该行对应的数据
        var item = _table.row($(this).closest('tr')).data();
        usersManage.currentItem = item;
        usersManage.show(item);
    });
    $paperTable.on("click", ".btn-edit", function() {
        //编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        //切换panel
        $("#edit-panel").show().siblings(".panel").hide();
        usersManage.edit(item);
    }).on("click", ".btn-delete", function() {
        //删除按钮
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        usersManage.delete(item);
    });


    $("#btn-advanced-search").click(function() {
        _table.draw();
    });

    $("#btn-add-people").click(function() {
        $("#add-id").val("");
        $("#add-name").val("");
        $("#add-sex").val("");
        $("#add-phone").val("");
        $("#add-email").val("");
        $("#add-position").val("");
        $("#add-introduction").val("");
        $("#add-grade").val("");
        $("#add-research_field").val("");
        $("#add-academic_title").val("");
        $("#add-admin_position").val("");
        $("#add-highest_degree").val("");
        $("#img-upload-preview").attr("src", "");
        $("#add-panel").show().siblings(".panel").hide();
    });
    $("#btn-edit-bt").click(function() {
        $("#edit-panel").show().siblings(".panel").hide();
    });
    $("#btn-add-save").click(function() {
        var people = {};
        // 这里的属性名还没改
        people.id = $("#add-id").val();
        people.name = $("#add-name").val();
        people.sex = $("#add-sex").val();
        people.phone = $("#add-phone").val();
        people.emaile = $("#add-email").val();
        people.position = $("#add-position").val();
        people.introduction = $("#add-introduction").val();
        people.academic_title = $("#add-academic_title").val();
        people.research_field = $("#add-research_field").val();
        people.grade = $("#add-grade").val();
        people.admin_position = $("#add-admin_position").val();
        people.highest_degree = $("#add-highest_degree").val();
        people.portrait = $("#img-upload-preview").attr("src");
        usersManage.save(people);
        _table.draw();
    });
    $("#btn-edit-save").click(function() {
        var people = {};
        people.id = $("#edit-id").val();
        people.name = $("#edit-name").val();
        people.phone = $("#edit-phone").val();
        people.email = $("#edit-email").val();
        people.sex = $("#edit-sex").val();
        people.position = $("#edit-position").val();
        people.introduction = $("#edit-introduction").val();
        people.academic_title = $("#edit-academic_title").val();
        people.research_field = $("#edit-research_field").val();
        people.grade = $("#edit-grade").val();
        people.admin_position = $("#edit-admin_position").val();
        people.highest_degree = $("#edit-highest_degree").val();
        people.portrait = $("#edit-img-upload-preview").attr("src");
        usersManage.save(people);
        _table.draw();
    });
    $("#btn-add-cancel").click(function() {
        $("#view-panel").show().siblings(".panel").hide();
    });
    $("#btn-edit-cancel").click(function() {
        $("#view-panel").show().siblings(".panel").hide();
    });
});
var usersManage = {
    currentItem: null,
    fuzzySearch: true,
    getQueryCondition: function(data) {
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
    show: function(item) {
        $("#view-id").text(item.id);
        $("#view-name").text(item.name);
        $("#view-sex").text(item.sex);
        $("#view-phone").text(item.phone);
        $("#view-portrait").text((item.id == null || item.id == undefined) ? "" : (urlGetImageById + "?id=" + item.portrait));
        $("#view-email").text(item.email);
        $("#view-position").text(item.position);
        $("#view-research_field").text(item.research_field);
        $("#view-academic_title").text(item.academic_title);
        $("#view-admin_position").text(item.admin_position);
        $("#view-highest_degree").text(item.highest_degree);
        $("#view-grade").text(item.grade);
        $("#view-introduction").text((item.id == null || item.id == undefined) ? "" : (urlGetDocumentById + "?id=" + item.introduction));
        //     var document = getDocumentById(item.introduction, urlGetDocumentById);
        //     var introduction;
        //     if (document === null || document == undefined || document.content == null || document.content == undefined)
        //         introduction = item.projectName + "<span style='color: red;'>该项目没有简介！</span>";
        //     $('#document-preview').html(introduction);
    },
    edit: function(item) {
        $("#edit-id").val(item.id);
        $("#edit-name").val(item.name);
        $("#edit-phone").val(item.phone);
        $("#edit-email").val(item.email);
        $("#edit-sex").val(item.sex);
        $("#edit-position").val(item.position);
        $("#edit-grade").val(item.grade);
        $("#edit-research_field").val(item.research_field);
        $("#edit-academic_title").val(item.academic_title);
        $("#edit-introduction").val((item.id == null || item.id == undefined) ? "" : (urlGetDocumentById + "?id=" + item.introduction));
        $("#edit-admin_position").val(item.admin_position);
        $("#edit-highest_degree").val(item.highest_degree);
        $("#edit-portrait").text((item.id == null || item.id == undefined) ? "" : (urlGetImageById + "?id=" + item.portrait));
    },
    save: function(people) {
        saveFun(urlSave, people)
    },
    delete: function(item) {
        var message = "确定删除人员：“" + item.name + "”的信息?";
        deleteFun(message, urlDelete, item.id);
    }
};
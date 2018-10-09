/*
* 作者：ricardo
* 描述：用户管理页面js
* 编写结构说明：
*     接口URL-->
*     页面加载-->
*     事件监听-->
*
**/
//获取用户分页数据
var urlGetPage="/users/page";
//获取user对应的people的信息
var urlGetPeopleById="";
//添加user用户
var urlAddUser="";
//更改用户信息
var urlUpdateUserById="";
//删除user用户
var urlDeleteUser="/users/delete";
//加载遮罩
var $wrapper = $('#users-table');

$(document).ready(function () {

    //users-table
    var $usersTable = $('#users-table');
    var  _table = $usersTable.dataTable(
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
                        data: "id",
                        width: "80px"
                    },
                    {
                        data: "username",
                        width: "150px"
                    },
                    {
                        data: "password",
                        width: "100px"
                    },
                    {
                        data: "roles",
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return data?"<span style='color: #0ae113;'>是</span>":"<span style='color: #e1b611;'>否</span>";
                        }
                    },
                    {
                        data: "updateTime",
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return dateToString(data);
                        }

                    },
                    {
                        className: "td-operation",
                        data: null,
                        width: "80px",
                        defaultContent: "",
                        orderable: false
                    }
                ],
                "createdRow": function (row, data, index) {
                    //行渲染回调,在这里可以对该行dom元素进行任何操作
                    // //给当前行加样式
                    // if (data.isTaoliao) {
                    //     $(row).addClass("text-info");
                    // }
                    // //给当前行某列加样式
                    // $('td', row).eq(9).addClass(classIsCutted(data.isCutted));
                    //不使用render，改用jquery文档操作呈现单元格
                    var $btnEdit = $('<button type="button" class="btn btn-small btn-warning btn-edit">修改</button>');
                    var $btnDelete = $('<button style="margin-left: 20px;"type="button" class="btn btn-small btn-danger btn-delete">删除</button>');
                    $('td', row).eq(5).append($btnEdit).append($btnDelete);

                },
                "drawCallback": function (settings) {
                    //默认选中第一行
                    $("tbody tr", $usersTable).eq(0).click();
                }
            })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    //行点击事件
    $("tbody", $usersTable).on("click", "tr", function (event) {
        $(this).addClass("active").siblings().removeClass("active");//有空再试试
        //$(this).addClass("active").siblings("tr").removeClass("active");
        // $("table tr").css('background-color','white');
        // $(this).css('background-color','blue');
        //获取该行对应的数据
        var item = _table.row($(this).closest('tr')).data();
        usersManage.currentItem = item;
        usersManage.showUser(item);
    });
    $usersTable.on("click", ".btn-edit", function () {
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
        // var workplace = getCookie("workplace");
        // var workplaceId = getCookie("workplaceId");
        // if(workplace===null||workplace===""){
        //     $.dialog.tips('请先选择派工工位！');
        //     return;
        // }
        // var message = "确定将批次:"+selectedItem.batchName+" 的单元:"+selectedItem.unitName+"派工给:"+workplace+"?";
        // $.dialog.confirm(message, function () {
        //     //$.dialog.tips("i am in!");
        //     $.ajax({
        //         type : "get",
        //         url : urlArrangeUnitToWorkPlace,
        //         data :"unitId=" + selectedItem.unitId+"&workplaceId="+workplaceId,
        //         async : false,
        //         success : function(data){
        //             $.dialog.tips(data.message);
        //         },
        //         error: function (XMLHttpRequest, textStatus, errorThrown) {
        //             $.dialog.alert("查询失败");
        //             $wrapper.spinModal(false);
        //         }
        //     });
        // });
    },
    deleteUser: function (item) {
        var message = "确定删除用户:"+item.username+"?";
        deleteFun(message,urlDeleteUser,item.id);
    }
};


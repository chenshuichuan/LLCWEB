/*
* 作者：ricardo
* 描述：图片管理页面js
* 编写结构说明：
*     接口URL-->
*     页面加载-->
*     事件监听-->
**/
//获取图片分页数据
var urlGetPage="/image/page";
//获取图片
var urlGetDocumentById="/image/getImageById";

//加载遮罩
var $wrapper = $('#images-table');
$(document).ready(function () {

    //images-table
    var $imagesTable = $('#images-table');
    var  _table = $imagesTable.dataTable(
        $.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION,
            {
                ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                    //手动控制遮罩
                    $wrapper.spinModal();
                    //封装请求参数
                    var param = documentManage.getQueryCondition(data);
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
                //空值的column会引发dataTable 报warning ：DataTables warning: table id=document-table - Requested unknown parameter 'author' for row 3.
                columns: [
                    {
                        data: "id",
                        width: "80px"
                    },
                    {
                        data: "url",
                        width: "200px",
                        render: function (data, type, row, meta) {
                            return (data===null||data==undefined)?'<i class="fa fa-file-image-o"></i>图片不存在':'<img src="'+data+'" class="table-img"/>';
                        }
                    },
                    {
                        data: "description",
                        width: "150px",
                        className: "ellipsis",	//文字过长时用省略号显示，CSS实现
                        render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
                    },
                    {
                        data: "createDate",
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return dateToString(data);
                        }
                    },
                    {
                        data: "url",
                        render: function (data, type, row, meta) {
                            return (data===null||data==undefined)?'<span class="text-danger">数据错误！</span>':'<a href="'+data+'"></a>';
                        }
                    },
                    {
                        className: "td-operation",
                        data: null,
                        width: "200px",
                        defaultContent: "",
                        orderable: false
                    }
                ],
                "createdRow": function (row, data, index) {

                    //不使用render，改用jquery文档操作呈现单元格
                    var $btnCopy = $('<button class="btn btn-info btn-copy" type="button" data-toggle="tooltip"data-placement="bottom" title="复制图片地址"> <i class="fa fa-copy"></i> </button>');
                    var $btnReview = $('<button class="btn btn-success btn-review" type="button" data-toggle="tooltip"data-placement="bottom" title="预览"> <i class="fa fa-file-image-o"></i> </button>');
                    var $btnEdit = $(' <button class="btn btn-warning btn-edit" type="button" data-toggle="tooltip"data-placement="bottom" title="编辑"> <i class="fa fa-edit"></i> </button>');
                    var $btnDelete = $('<button class="btn btn-danger  btn-delete" type="button" data-toggle="tooltip"data-placement="bottom" title="删除"> <i class="fa fa-trash-o"></i> </button>');

                    $('td', row).eq(5).append($btnCopy).append($btnReview).append($btnEdit).append($btnDelete);

                },
                "drawCallback": function (settings) {
                    //默认选中第一行
                    $("tbody tr", $imagesTable).eq(0).click();
                }
            })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    //行点击事件//可以对图片进行预览
    // $("tbody", $imagesTable).on("click", "tr", function (event) {
    //     $(this).addClass("active").siblings().removeClass("active");
    //     // $("table tr").css('background-color','white');
    //     // $(this).css('background-color','blue');
    //     //获取该行对应的数据
    //     var item = _table.row($(this).closest('tr')).data();
    //     documentManage.currentItem = item;
    //     documentManage.showDocument(item);
    // });
    $imagesTable.on("click", ".btn-copy", function () {
        //复制图片url
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        documentManage.deleteDocument(item);
    }).on("click", ".btn-review", function () {
        //预览图片
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        documentManage.deleteDocument(item);
    }).on("click", ".btn-edit", function () {
        //编辑图片信息
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");

        documentManage.editDocument(item);
    }).on("click", ".btn-delete", function () {
        //删除图片
        var item = _table.row($(this).closest('tr')).data();
        $(this).closest('tr').addClass("active").siblings().removeClass("active");
        documentManage.deleteDocument(item);
    });


    $("#btn-search").click(function () {
        documentManage.fuzzySearch=  true;
        _table.draw();
    });
    $("#btn-advanced-search").click(function () {
         documentManage.fuzzySearch=  false;
        //_table.draw();
    });
    $("#btn-add-document").click(function () {
       //显示图片上传页面
        $("#div-upload-image").slideToggle("fast");
    });

});


var documentManage = {
    currentItem: null,
    fuzzySearch: true,
    getQueryCondition: function (data) {
        var param = {};
        //模糊查找标志，当有高级查找时，标记为false
        param.fuzzySearch = documentManage.fuzzySearch;
        param.fuzzy = $("#search-value").val();
        //高级查找封装参数(待完成)

        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.draw = data.draw;
        return param;
    },
    showDocument: function (item) {
        //$("#view-content").html("show content test<span style='color: #e12fab;'>span test"+item.id+"</span>");
        if(item!=null||item !=undefined){
            var document = getDocumentById(item.id);
            if(document!==null) $("#view-content").html(document.content);
            else $("#view-content").html("未找到文档内容！");
        }
        else $("#view-content").html("");
    },
    editDocument: function (item) {
        //$.dialog.tips("edit test");
        //转跳到文档编辑界面 ,新增文档传入id为0
        window.location.href=urlEditDocumentById+"?id="+item.id;
    },

    deleteDocument: function (item) {
        $.dialog.tips("delete test");
    }
};
//根据id获取document信息
function getDocumentById(id) {
    var document =null;
    //设置同步
    $.ajax({
        type : "get",
        url : urlGetDocumentById,
        data :"id=" + id,
        async : false,
        success : function(data){
            document = data.data;
            if(data.result!==1){
                $.dialog.tips(data.message);
            }
        }
    });
    return document;
}


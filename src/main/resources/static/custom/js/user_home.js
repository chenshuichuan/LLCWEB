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
var urlGetPage="/activity/page";
//更改
var urlSave="/activity/save";
//删除
var urlDelete="/activity/delete";
//获取document对应的内容信息
var urlGetDocumentById="/document/getDocumentById";


var urlGetAllDocuments="/document/getAll";


$(document).ready(function () {

    $("#btn-edit-profile").click(function () {
        $("#add-panel").slideToggle("fast");
    });
    $("#btn-edit-cancel").click(function () {
        $("#add-panel").slideToggle("fast");
        //$("#add-panel").show().siblings(".panel").show();
    });
    //保存简介文档更改
    $("#btn-edit-save").click(function () {

    });
    $("#btn-refresh-selector").click(function () {
        usersManage.initCompenets();
    });
    $("#btn-edit-document").click(function () {
        var id = $("#add-introduction").val();
        if(id==null||id==undefined||id.length==0)
            id = '0';
        window.open('/admin/edit.html?id='+id);
    });
    $("#add-introduction").onChange(function () {
        //加载个人简介
        usersManage.loadDocument($("#add-introduction").val());
    });
    usersManage.initCompenets();
    //加载个人简介
    usersManage.loadDocument(person.introduction);
});


var usersManage = {
    //渲染页面控件
    initCompenets:function(){

        //渲染document选择框
        var documentList = getAllDocuments(urlGetAllDocuments);
        var selector = $("#add-introduction");
        selector.empty();
        var options = "<option value='0'> 新建文档</option>";

        for (var i=0; i<documentList.length;i++){
            //这里的是否空闲渲染存在问题
            options+= "<option"+
                " value='"+documentList[i].id+ "'>"+documentList[i].title
                +"</option>";
        }
        selector.append(options);
        //设置为当前文档
        selector.val(person.introduction);
        selector.selectpicker('refresh');
    },
    loadDocument: function (id) {
        var document = getDocumentById(id,urlGetDocumentById);
        var introduction;
        if(document===null||document==undefined||document.content==null||document.content==undefined)
            introduction="<span style='color: red;'>没有简介！</span>";
        $('#document-preview').html(introduction);
    },
    edit: function (item) {
        $("#add-panel-title").text("编辑项目记录");
        $("#add-id").val(item.id);

        $("#add-introduction").val(item.introduction);
        //更多
    },
    save: function () {
        var project={};
        project.id=$("#add-id").val();


        project.startDate=$("#add-start_date").val();
        project.endDate= $("#add-end_date").val();

        project.introduction=$("#add-introduction").val();
        saveFun(urlSave,project)
    }
};


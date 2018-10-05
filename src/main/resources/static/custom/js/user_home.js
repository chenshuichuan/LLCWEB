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


});


var usersManage = {
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
    },
    delete: function (item) {
        var message ="确定删除项目：“"+item.projectName+"”的信息?";
        deleteFun(message,urlDelete,item.id);
    }
};


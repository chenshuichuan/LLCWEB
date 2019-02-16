/*
* 作者：ricardo
* 描述：首页js
* 编写结构说明：
*     接口URL-->
*     页面加载-->
*     事件监听-->
*
**/

$(document).ready(function () {


    //公告通知
    var infor = getTopMes("/activity/coreDynamics",6);
    fillTheInfo("#con1", infor,"/activity?id=");

    //科学研究
    var projects = getTopMes("/project/getLatest",7);
    fillTheModel("#ul-project", projects,"/project_brief.html?id=");
    //学术交流
    var academicData = getTopMes("/activity/coreDynamics",6);
    fillTheModel("#ul-academic-exchange", academicData,"/activity?id=");

    //论文
    var paperMes = getTopMes("/paper/getLatest",5);
    fillTheProduct("#div-first-paper", "#ul-paper", paperMes,"/paper?id=");
    //专利
    paperMes = getTopMes("/patent/getLatest",5);
    fillTheProduct("#div-first-patent", "#ul-patent", paperMes,"/patent?id=");
    //软著
    paperMes = getTopMes("/software/getLatest",5);
    fillTheProduct("#div-first-software", "#ul-software", paperMes,"/software?id=");

    // console.log(paperMes);
    // pushMes('#paper',paperMes);
    // //专利
    // var patentMes = getTopMes("/patent/getLatest",5);
    // console.log(patentMes);
    // pushMes('#patent',patentMes);
    // //软件著作权
    // var softwareMes = getTopMes("/software/getSoftwares",5);
    // console.log(softwareMes);
    // pushMes('#copyright',softwareMes);

    //中心动态
    var dynamicMes = getTopMes("/activity/coreDynamics",6);
    fillTheModel("#ul-activity", dynamicMes,"/activity?id=");
    //文体活动
    dynamicMes = getTopMes("/activity/coreDynamics",6);
    fillTheModel("#ul-activity2", dynamicMes,"/activity?id=");

    //开放课题
    var openPro = getTopMes("/project/getLatest",6);
    fillTheModel("#ul-open-project", openPro,"/activity?id=");

});
$(function(){

});
//获取首页推送消息
function getTopMes(url,num){
    var message =null;
    //设置同步
    $.ajax({
        type : "get",
        url : url,
        data : "count="+num,
        async : false,
        success : function(data){
            if(data.result!=1){
                alert(data.message);
            }
            else{
                message = data.data;
            }
        }
    });
    return message;
}
//更新推送消息
function pushMes(id,message){
    var num;
    ($(id).find('.item1').find('a').length > message.length)?num = message.length:num = $(id).find('.item1').find('a').length;
    $(id).find('.item1').find('a').html("");
    if(message.length == 0)     return ;
    for(var i = 0 ; i < num; i++){
        $(id).find('.item1').find('a').eq(i).html(message[i].title);
    }
    if(num ==  message.length){
        for(var j = num; j <= $(id).find('.item1').find('a').length+1; j++){
            $(id).find('.item1').eq(j).html("");
        }
    }
}



//填充滚动通知栏数据
function fillTheInfo(id, data,pre_url) {
    var father = $(id);
    father.empty();
    for(var i = 0 ; i < data.length; i++){
        var date = new Date(data[i].date);
        var str = "<li>\n" +
            "       <a href=\""+pre_url+data[i].id+"\">"+data[i].title+"</a>\n" +
            "     </li>";
        father.append(str);
    }
}
//根据传入的首页模块id以及数据，填充模块内容，根据pre_url设置跳转链接
function fillTheModel(id, data,pre_url) {
    var father = $(id);
    father.empty();
    for(var i = 0 ; i < data.length; i++){
        var date = new Date(data[i].date);
        var str = "<li class=\"item item1\">" +
            "        <i></i>" +
            "        <a href=\""+pre_url+data[i].id+"\">"+data[i].title+"</a>" +
            "        <span class=\"item-time\">"+date.getFullYear()+"/"+date.getMonth()+"/"+date.getDate()+"</span>\n" +
            "        </li>";
        father.append(str);
    }
}
//根据传入的首页模块id以及数据，填充模块内容，根据pre_url设置跳转链接
function fillTheProduct(first_id, id, data,pre_url) {
    if(data.length<1)return;
    //填充第一篇
    var first = $(first_id);
    first.empty();
    var date = new Date(data[0].date);
    console.log(date);
    var str1 = " <div class=\"lately-date fl\">\n" +
        "                    <span class=\"date-day\">"+date.getDate()+"</span>\n" +
        "                    <span class=\"date-other\">"+date.getFullYear()+"-"+(date.getMonth()+1)+"</span>\n" +
        "                </div>\n" +
        "                <div class=\"lately-cont item1\">\n" +
        "                    <p class=\"lately-title\"><a>"+data[0].title+"</a></p>\n" +
        //"                    <span class=\"lately-des\">"+data[0].authorList+"</span>\n" +
        "                </div>";
    first.append(str1);

    //填充剩下的
    var father = $(id);
    father.empty();
    for(var i = 1 ; i < data.length; i++){
        date = new Date(data[i].date);
        var str = "<li class=\"item item1\">" +
            "        <i></i>" +
            "        <a href=\""+pre_url+data[i].id+"\">"+data[i].title+"</a>" +
            "        <span class=\"item-time\">"+date.getFullYear()+"/"+date.getMonth()+"/"+date.getDate()+"</span>\n" +
            "        </li>";
        father.append(str);
    }
}
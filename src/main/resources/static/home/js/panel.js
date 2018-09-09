/**
 * Created by BlueFisher on 2016/10/30 0030.
 */
/**
 * Created by BlueFisher on 2016/10/15 0015.
 */
$(document).ready(function () {
    fillPanel();
});
function fillPanel(){
    $('.panel-title>a[aria-controls="collapseOne"]').append('文档管理');
    $('dd#paper').append('<a href="#/paper" class="navList_href">论文管理</a>');
    $('dd#paper').append('<a href="#/pattern" class="navList_href">专利管理</a>');
    $('dd#paper').append('<a href="#/software" class="navList_href">软著管理</a>');
    $('dd#paper').append('<a href="#/prize" class="navList_href">奖励管理</a>');
    $('dd#project').append('<a href="#/project/teamNum=1" class="navList_href">项目管理</a>');
    $('dd#conference').append('<a href="#/conference" class="navList_href">会议管理</a>');
    $('dd#student').append('<a href="#/student" class="navList_href">学生管理</a>');
    $('dd#teacher').append('<a href="#/teacher" class="navList_href">老师管理</a>');
    $('dd#pic').append('<a href="indexPic.html" class="navList_href">图片管理</a>');

    $('.panel-title>a[aria-controls="collapseTwo"]').append('用户管理');
    $('dd#authority').append('<a href="#" class="navList_href">用户权限</a>');
    $('dd#manage').append('<a href="#" class="navList_href">用户管理</a>');
}

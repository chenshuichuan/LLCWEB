/**
 * Created by BlueFisher on 2016/12/16 0016.
 */
$(document).ready(function () {
    // var prize_url = "../limitPrize?currentPage=1&pageSize=15";
    // gainJSON(prize_url, prizeJSON);
    var tableAPP = angular.module('tableApp', ['ngRoute']);

    tableAPP.factory('prizeData', function () {
        var priceInfo = {};
        $.ajaxSettings.async = false;
        $.getJSON('../limitPrize?currentPage=1&pageSize=15', function (obj) {
            priceInfo.data = obj.data.resultList;
        });

        return priceInfo;
        $.ajaxSettings.async = true;
    });
    
    tableAPP.service('prizeService',function () {
        
    });

    tableAPP.controller('prizeCtrl', function ($scope, $filter, $http,$route) {
        $scope.$route=$route;
        $http.get("../limitPrize?currentPage=1&pageSize=15")
            .success(function (response) {
                $scope.prizeInfo=response.data.resultList;
            });

        // $scope.prizeInfo = prizeData;
        $scope.converTime = function (time) {
            var date = new Date(time).toLocaleDateString();
            return converDate(date);
        };

        $scope.orderType = 'id';
        $scope.order = '';

        /****************************************改变顺序事件*******************************************/
        $scope.changeOrder = function (type) {
            $scope.orderType = type;
            if ($scope.order === '') {
                $scope.order = '-';
            } else {
                $scope.order = '';
            }
        };

        /****************************************添加事件*******************************************/
        $scope.add = function () {
            $(".content,#first").addClass("disappear");
            $(".content_edit").removeClass("appear");
            $(".save").addClass("disappear");
            $(".submit").removeClass("appear");
            $scope.prizeDisplayInfo = {};
            console.log($scope.prizeDisplayInfo);
        };

        /****************************************填充编辑页面*******************************************/
        $scope.edit = function (id) {
            var index = -1;
            angular.forEach($scope.prizeInfo, function (item, key) {
                if (item.id === id) {
                    index = key;
                }
            });
            $(".content").addClass("disappear");
            $(".content_edit").removeClass("appear");
            $(".save,#first").removeClass("disappear");
            $(".submit").addClass("appear");
            $scope.prizeDisplayInfo = $scope.prizeInfo[index];
            $scope.prizeDisplayInfo.time = $scope.converTime($scope.prizeDisplayInfo.time);
            console.log($scope.prizeDisplayInfo);
        };

        /****************************************删除事件*******************************************/
        $scope.del = function (index, id) {
            $.getJSON('../admin/deletePrize?id=' + id, function (obj) {
                if (obj.errorCode != "0x03") {
                    $scope.prizeInfo.splice(index, 1);
                    // window.location.reload();           //成功后刷新页面
                    alert("删除成功！！！");
                }
                else
                    alert("无权访问！！！");
            });
        };

        /****************************************确认是否删除事件*******************************************/
        $scope.firmDel = function (id) {
            var index = -1;
            angular.forEach($scope.prizeInfo.data, function (item, key) {
                if (item.id === id) {
                    index = key;
                }
            });
            //利用对话框返回的值 （true 或者 false）
            if (confirm("你确定删除吗？")) {
                $scope.del(index, id);
            }
            else {

            }
        };

        /****************************************保存事件*******************************************/
        $scope.save = function () {
            $(".content").removeClass("disappear");
            $(".content_edit").addClass("appear");
            //$(".search_text input").attr("value", genJson());
            $scope.sentJSON('modifyPrize');
            window.location.reload();           //成功后刷新页面
        };

        /****************************************提交事件*******************************************/
        $scope.submit = function () {
            $(".content").removeClass("disappear");
            $(".content_edit").addClass("appear");
            $scope.sentJSON("addPrize");
            window.location.reload();           //成功后刷新页面
        };

        /****************************************发送JSON到后台*******************************************/
        $scope.sentJSON = function (type) {
            var data = $filter('json')($scope.prizeDisplayInfo);
            $.ajax({
                type: 'POST',
                contentType: "application/json",
                url: '/admin/' + type,
                data: data,
                success: function (obj) {
                    if (obj.errorCode != "0x03") {
                        // window.location.reload();           //成功后刷新页面
                        alert('操作成功！！！');
                    }
                    else
                        alert('无权访问！！！');
                },
                error: function () {
                    alert(data);
                }
            });
        };

    });

    tableAPP.config(['$routeProvider',function ($routeProvider) {
        $routeProvider
            .when('/paper',{templateUrl:'tpls/paper.html',controller:'prizeCtrl'})
            .when('/pattern',{templateUrl:'tpls/pattern.html',controller:'prizeCtrl'})
            .when('/prize',{templateUrl:'tpls/prize.html',controller:'prizeCtrl'})
            .otherwise({redirectTo:'/prize'});
    }]);

    /****************************************分页*******************************************/
    $("#demo4").paginate({
        count: page(),      //totalPage
        start: 1,
        display: 10,
        border: false,
        text_color: '#1ABC9C',
        background_color: 'none',
        text_hover_color: '#107863',
        background_hover_color: 'none',
        images: false,
        mouse: 'press',
        onChange: function (page) {
            getPaperPage(page);
            $('.paperTable').addClass('tableContent');
            $('.searchTable').removeClass('tableContent');
        }
    });

    /****************************************日历插件*******************************************/
    calendar();

    /****************************************高级搜索页面弹出与收起*******************************************/
    adSearch();

});

/****************************************填充论文表单*******************************************/
function prizeJSON(obj) {
    if (obj.status) {
        $("tbody.paperTable>tr").remove();
        // fillPaperTable(obj, "paper");
        // fillPrizeTable();
    }
}

/****************************************填充表格方法*******************************************/
function fillPaperTable(obj, type) {
    $.each(obj.data.resultList, function (i, item) {
        $("tbody." + type + "Table").append("<tr id=" + i + ">");
        var date = new Date(item.time).toLocaleDateString();
        var d = converDate(date);
        $("tbody." + type + "Table tr").eq(i).append("<td id='id'>" + item.id + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='project'>" + item.project + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='prize_title' class='ellipsis'>" + item.prizeTitle + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='name'>" + item.name + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='content' class='ellipsis'>" + item.content + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='time'>" + d + "</td>");
        // $("tbody.paperTable tr").eq(i).append("<td id='paper_url'>" + item.paper_url + "</td>");
        $("tbody." + type + "Table tr").eq(i).append("<td id='ope" + i + "'>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#' class='edit' onclick='edit(this)' id='" + i + "'></a>");
        $("tbody." + type + "Table tr").find("#ope" + i).append("<a href='#' class='del' onclick='firmDel(this)' id='" + i + "'></a>");
    });
}

function converTime(time) {
    var date = new Date(item).toLocaleDateString();
    var d = converDate(date);
    return d;
}

function fillPrizeTable() {
    $('.paperTable').append("<tr ng-repeat='item in prizeInfo'>");
    $("tbody.paperTable tr").append("<td>{{item.id}}</td>");
    $("tbody.paperTable tr").append("<td>{{item.prizeTitle}}</td>");
    $("tbody.paperTable tr").append("<td>{{item.time}}</td>");
    $("tbody.paperTable tr").append("<td>{{item.name}}</td>");
    $("tbody.paperTable tr").append("<td>{{item.project}}</td>");
    $("tbody.paperTable tr").append("<td>{{item.content}}</td>");
}

/****************************************页面按键事件*******************************************/
function getPaperPage(page) {
    var paper_url = "../limitPrize?currentPage=" + page + "&pageSize=15";
    getPrizeByPage(page, 15, prizeJSON);
}

/****************************************获取总共的页数*******************************************/
function page() {
    var totalPage;
    $.ajax({
        url: '../limitPrize?currentPage=1&pageSize=15',
        async: false,
        dataType: 'json',
        success: function (obj) {
            totalPage = obj.data.totalPage;
        }
    });
    return totalPage;
}

// /****************************************填充编辑页面*******************************************/
// function edit(obj) {
//     $(".content").addClass("disappear");
//     $(".content_edit").removeClass("appear");
//     $(".save,#first").removeClass("disappear");
//     $(".submit").addClass("appear");
//     // var idValue = $(obj).attr("id");
//     // fill("id", idValue);
//     // fill("project", idValue);
//     // fill("prize_title", idValue);
//     // fill("name", idValue);
//     // fill("content", idValue);
//     // fill("time", idValue);
// }

// /****************************************确认是否删除事件*******************************************/
// function firmDel(obj) {
//     //利用对话框返回的值 （true 或者 false）
//     if (confirm("你确定删除吗？")) {
//         del(obj,'Prize');
//     }
//     else {
//
//     }
// }

/****************************************封装数组对象为JSON*******************************************/
function genJson() {
    var obj = new Object();

    var strDate = $(".input_time").attr('value');
    var d = convertDateToJsonDate(strDate);

    obj.id = $(".input_id").val();
    obj.project = $(".input_project").val();
    obj.prizeTitle = $(".input_prize_title").val();
    obj.name = $(".input_name").val();
    obj.content = $(".input_content").val();
    obj.time = d;

    var postdata = JSON.stringify(obj);
    return postdata;

}

/****************************************保存事件*******************************************/
// function save() {
//     $(".content").removeClass("disappear");
//     $(".content_edit").addClass("appear");
//     //$(".search_text input").attr("value", genJson());
//     sentJSON('modifyPrize');
// }

/****************************************提交事件*******************************************/
function submit() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    sentJSON("addPrize");
}

/****************************************发送JSON到后台*******************************************/
// function sentJSON(type) {
//     $.ajax({
//         type: 'POST',
//         contentType: "application/json",
//         url: '/admin/' + type,
//         data: genJson(),
//         success: function (obj) {
//             if (obj.errorCode != "0x03") {
//                 // window.location.reload();           //成功后刷新页面
//                 alert('操作成功！！！');
//                 var pattern_url = "../limitPrize?currentPage=1&pageSize=15";
//                 gainJSON(pattern_url, prizeJSON);
//             }
//             else
//                 alert('无权访问！！！');
//         }
//     });
// }

/****************************************添加事件*******************************************/
function add() {
    $(".content,#first").addClass("disappear");
    $(".content_edit").removeClass("appear");
    $(".save").addClass("disappear");
    $(".submit").removeClass("appear");

    clear();

    getInput("id");
    getInput("project");
    getInput("prize_title");
    getInput("name");
    getInput("content");
    getInput("time");
}

/****************************************通过回车触发的搜索事件*******************************************/
function searchByEnter(event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;       //处理兼容性问题
    $("input[name='search']").bind('input propertychange', function () {
        $("input[name='search']").attr("value", $(this).val());
    });
    if (keyCode == "13") {
        searchFill();
    }
}

/****************************************通过鼠标触发的搜索事件*******************************************/
function searchByClick() {
    $("input[name='search']").bind('input propertychange', function () {
        $("input[name='search']").attr("value", $(this).val());
    });
    searchFill();
}

/****************************************搜索事件的填充方法*******************************************/
function searchFill() {
    $('.searchTable').html("");
    var obj = new Object();
    obj.title = $("input[name='search']").val();
    var postdata = JSON.stringify(obj);
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '../limitPrize?currentPage=1&pageSize=100',
        data: postdata,
        success: function (obj) {
            $('.paperTable').addClass('disappear').removeClass('tableContent');
            $('.searchTable').removeClass('appear').addClass('tableContent');
            fillPaperTable(obj, "search");
        }
    });
}

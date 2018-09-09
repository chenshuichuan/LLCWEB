/**
 * Created by BlueFisher on 2017/1/7 0007.
 */
var tableCtrl = angular.module('tableCtrl', []);

tableCtrl.controller('PrizeCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../limitPrize?currentPage=1&pageSize=15")
            .success(function (response) {
                $scope.prizeInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('PaperCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../limitPapers?currentPage=1&pageSize=15")
            .success(function (response) {
                console.log(response);
                $scope.paperInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('PatternCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../limitPatterns?currentPage=1&pageSize=15")
            .success(function (response) {
                console.log(response);
                $scope.patternInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('SoftwareCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../limitSoftWares?currentPage=1&pageSize=15")
            .success(function (response) {
                console.log(response);
                $scope.softwareInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('ConferenceCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../limitConference?currentPage=1&pageSize=15")
            .success(function (response) {
                console.log(response);
                $scope.conferenceInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('TeacherCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../teachers")
            .success(function (response) {
                console.log(response);
                $scope.teacherInfo = response.data;
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

    }
]);

tableCtrl.controller('ProjectCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        $scope.$route = $route;
        $http.get("../getProjectLimit?teamNum=1&currentPage=1&pageSize=15")
            .success(function (response) {
                console.log(response);
                $scope.projectInfo = response.data.resultList;
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

    }
]);

tableCtrl.controller('StudentCtrl', ['$scope', '$filter', '$http', '$route',
    function ($scope, $filter, $http, $route) {
        // var studentUrl = window.location.hash;
        // studentUrl = studentUrl.split('/');
        // $scope.gradeValue=studentUrl[3];
        // $scope.degreeValue=studentUrl[2];
        $scope.gradeCode=2016;
        $scope.degreeCode=1;
        $scope.$route = $route;

        $scope.degree = ["学位","博士研究生","硕士研究生"];
        $scope.grade=["入学年份"];
        for(var i=2000;i<=new Date().getFullYear();i++){
            $scope.grade.push(i);
        };
        $http.get("../stu_DandG?grade="+$scope.gradeCode+"&degree="+$scope.degreeCode)
            .success(function (response) {
                console.log(response);
                $scope.studentInfo = response.data;
            });

        // $scope.prizeInfo = prizeData;
        $scope.converTime = function (time) {
            var date = new Date(time).toLocaleDateString();
            return converDate(date);
        };

        $scope.orderType = 'id';
        $scope.order = '';

        /****************************************判断项目名称方法*******************************************/
        $scope.degreeNum = function () {
            $scope.degreeCode = $('#degree').find('option:selected').attr('value');
            $http.get("../stu_DandG?grade="+$scope.gradeCode+"&degree="+$scope.degreeCode)
                .success(function (response) {
                    console.log(response);
                    $scope.studentInfo = response.data;
                });
            // window.location.href = '#/student/'+$scope.degreeCode+'/'+$scope.gradeValue;
        };

        $scope.gradeNum=function() {
            $scope.gradeCode = $('#grade').find('option:selected').text();
            $http.get("../stu_DandG?grade="+$scope.gradeCode+"&degree="+$scope.degreeCode)
                .success(function (response) {
                    console.log(response);
                    $scope.studentInfo = response.data;
                });
            // window.location.href = '#/student/'+$scope.degreeValue+'/'+$scope.gradeCode;
        };

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

    }
]);

/**
 * 这里接着往下写，如果控制器的数量非常多，需要分给多个开发者，可以借助于grunt来合并代码
 */
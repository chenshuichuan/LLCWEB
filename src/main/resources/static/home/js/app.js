/**
 * Created by BlueFisher on 2017/1/7 0007.
 */
var tableApp = angular.module('tableApp', ['ngRoute','tableCtrl']);

tableApp.config(function ($routeProvider) {
    $routeProvider
        .when('/paper', {
            templateUrl: 'tpls/paper.html',
            controller: 'PaperCtrl'
        })
        .when('/pattern', {
            templateUrl: 'tpls/pattern.html',
            controller: 'PatternCtrl'
        })
        .when('/software',{
            templateUrl: 'tpls/software.html',
            controller: 'SoftwareCtrl'
        })
        .when('/prize', {
            templateUrl: 'tpls/prize.html',
            controller: 'PrizeCtrl'
        })
        .when('/conference',{
            templateUrl: 'tpls/conference.html',
            controller: 'ConferenceCtrl'
        })
        .when('/teacher',{
            templateUrl: 'tpls/teacher.html',
            controller: 'TeacherCtrl'
        })
        .when('/project/teamNum=1',{
            templateUrl: 'tpls/project.html',
            controller: 'ProjectCtrl'
        })
        .when('/student',{
            templateUrl: 'tpls/student.html',
            controller: 'StudentCtrl'
        })
        .when('/student/2/2016',{
            templateUrl: 'tpls/student.html',
            controller: 'StudentCtrl'
        });
        // .otherwise({redirectTo: '/paper'});
});

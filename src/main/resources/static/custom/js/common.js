/**
 * Created by ricardo on 2018/8/10.
 * 通用js文件，全局
 */
//设置cookie
function setCookie(name, value, day) {
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires=' + date;
};

//获取cookie
function getCookie(name) {
    var reg = RegExp(name + '=([^;]+)');
    var arr = document.cookie.match(reg);
    if (arr) {
        return arr[1];
    } else {
        return '';
    }
};

//删除cookie
function delCookie(name) {
    setCookie(name, null, -1);
};

/*复制UrlText内容到系统的剪切板*/
function copyUrls(UrlText) {
    if(UrlText===null||UrlText==undefined){
        $.dialog.tips("数据无效！复制失败！");
        return;
    }
    let textArea = document.createElement("textarea");
    textArea.style.position = 'fixed';
    textArea.style.top = 0;
    textArea.style.left = 0;
    textArea.style.width = '2em';
    textArea.style.height = '2em';
    textArea.style.padding = 0;
    textArea.style.border = 'none';
    textArea.style.outline = 'none';
    textArea.style.boxShadow = 'none';
    textArea.style.background = 'transparent';
    textArea.value = UrlText;
    document.body.appendChild(textArea);
    textArea.select();
    try {
        document.execCommand('copy');
        $.dialog.tips("复制成功");
    } catch (err) {
        this.throwError('不能使用这种方法复制内容'+err.toString());
    }
    document.body.removeChild(textArea)
}
/*图片上传预览图片*/
function imgChange(obj,previewId) {
    //判断浏览器是否支持FileReader接口
    if (typeof FileReader == 'undefined') {
        $.dialog.alert("当前浏览器不支持FileReader接口!");
        return;
        //使选择控件不可操作
        // document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
    }
    var file = obj.files[0];
    // console.log(obj);console.log(file);
    // console.log("file.size = " + file.size);  //file.size 单位为byte
    //var fileSize = file.size/1024.00;
    //document.getElementById("file-size").text();
    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    };
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    };
    reader.onabort = function (e) {
        console.log("中断读取....");
    };
    reader.onerror = function (e) {
        console.log("读取异常....");
    };
    reader.onload = function (e) {
        console.log("成功读取....");

        var img = document.getElementById(previewId);
        img.src = e.target.result;
        //或者 img.src = this.result;  //e.target == this
    };

    reader.readAsDataURL(file);
}
/*dataTable常量*/
Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate();
};
//后台传来的日期数据，转化为2012/08/02 的格式
function dateToString(date) {
    if(date===null)return "未知";
    else return new Date(date).toLocaleString();
}

$(document).ready(function(){
    $(".dropdown-button").dropdown();
    $("#sideNav").click(function(){
        if($(this).hasClass('closed')){
            $('.navbar-side').animate({left: '0px'});
            $(this).removeClass('closed');
            $('#page-wrapper').animate({'margin-left' : '260px'});

        }
        else{
            $(this).addClass('closed');
            $('.navbar-side').animate({left: '-260px'});
            $('#page-wrapper').animate({'margin-left' : '0px'});
        }
    });
    /*MENU
              ------------------------------------*/
    $('#main-menu').metisMenu();

    $(window).bind("load resize", function () {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse')
        } else {
            $('div.sidebar-collapse').removeClass('collapse')
        }
    });
});


// panel collapsible 面板折叠
$('.panel .tools .fa').click(function () {
    var el = $(this).parents(".panel").children(".panel-body");
    if ($(this).hasClass("fa-chevron-down")) {
        $(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
        el.slideUp(200);
    } else {
        $(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
        el.slideDown(200); }
});

//表格配置
var CONSTANT = {
    DATA_TABLES : {
        DEFAULT_OPTION : { //DataTables初始化选项
            language: {
                "sProcessing":   "处理中...",
                "sLengthMenu":   "每页 _MENU_ 项",
                "sZeroRecords":  "没有匹配结果",
                "sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                "sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix":  "",
                "sSearch":       "搜索:",
                "sUrl":          "",
                "sEmptyTable":     "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands":  ",",
                "oPaginate": {
                    "sFirst":    "首页",
                    "sPrevious": "上页",
                    "sNext":     "下页",
                    "sLast":     "末页",
                    "sJump":     "跳转"
                },
                "oAria": {
                    "sSortAscending":  ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            autoWidth: false,	//禁用自动调整列宽
            stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
            order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
            processing: false,	//隐藏加载提示,自行处理
            serverSide: true,	//启用服务器端分页
            searching: false	//禁用原生搜索
        },
        COLUMN: {
            CHECKBOX: {	//复选框单元格
                className: "td-checkbox",
                orderable: false,
                width: "30px",
                data: null,
                render: function (data, type, row, meta) {
                    return '<input type="checkbox" class="iCheck">';
                }
            }
        },
        RENDER: {	//常用render可以抽取出来，如日期时间、头像等
            ELLIPSIS: function (data, type, row, meta) {
                data = data||"";
                return '<span title="' + data + '">' + data + '</span>';
            }
        }
    }
};


//删除通用函数
function deleteFun(message,urlDelete,id) {
    $.dialog.confirm(message, function () {
        $.ajax({
            type : "get",
            url : urlDelete,
            data :"id=" + id,
            async : false,
            success : function(data){
                $.dialog.tips(data.message);
            }
        });
    });
}
//保存通用函数
function saveFun(urlSave,myData) {
    $.ajax({
        type : "post",
        url : urlSave,
        data :myData,
        async : false,
        success : function(data){
            $.dialog.tips(data.message);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.dialog.alert("更新失败！");
        }
    });
}
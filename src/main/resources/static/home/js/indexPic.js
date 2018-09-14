/**
 * Created by BlueFisher on 2016/10/30 0030.
 */
jQuery(document).ready(function ($) {
    // browser window scroll (in pixels) after which the "back to top" link is shown
    var offset = 300,
    //browser window scroll (in pixels) after which the "back to top" link opacity is reduced
        offset_opacity = 1200,
    //duration of the top scrolling animation (in ms)
        scroll_top_duration = 700,
    //grab the "back to top" link
        $back_to_top = $('.cd-top');

    //hide or show the "back to top" link
    $(window).scroll(function () {
        ($(this).scrollTop() > offset) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
        if ($(this).scrollTop() > offset_opacity) {
            $back_to_top.addClass('cd-fade-out');
        }
    });
    //www.sucaijiayuan.com
    //smooth scroll to top
    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body,html').animate({
                scrollTop: 0,
            }, scroll_top_duration
        );
    });


    /****************************************计算图片瀑布流数量*******************************************/
    var ghostNode = $('#masonry_ghost').find('.thumbnail'), i, ghostIndexArray = [], j;
    var ghostCount = ghostNode.length;
    //alert(ghostNode.length);
    j = ghostCount - 1;
    for (i = 0; i < ghostCount; i++) {
        ghostIndexArray[i] = j;
        j--;
    }
    // ghostIndexArray.sort(function (a, b) {
    //     if (Math.random() > 0.5) {
    //         return a - b;
    //     } else {
    //         return b - a;
    //     }
    // });

    var currentIndex = 0;
    var masNode = $('#masonry');
    var imagesLoading = false;
    initMasonry();
    function getNewItems() {
        var newItemContainer = $('<div/>');
        for (var i = 0; i < 15; i++) {
            if (currentIndex < ghostCount) {
                newItemContainer.append(ghostNode.get(ghostIndexArray[currentIndex]));
                currentIndex++;
            }
        }
        return newItemContainer.find('.thumbnail');
    }

    function processNewItems(items) {
        items.each(function () {
            var $this = $(this);
            var idValue = $this.attr('id');
            idValue = idValue.substring(3, 5);
            var imgsNode = $this.find('.imgs');
            var title = $this.find('.title').text();
            var lightboxName = 'lightbox_'; // + imgNames[0];
            imgsNode.append('<a href="../getPic?id=' + idValue + '" data-lightbox="' + lightboxName + '" title="' + title + '"><img src="/getPic?id=' + idValue + '" /></a>');
        });
    }

    function initMasonry() {
        var items = getNewItems().css('opacity', 0);
        processNewItems(items);
        masNode.append(items);

        imagesLoading = true;
        items.imagesLoaded(function () {
            imagesLoading = false;
            items.css('opacity', 1);
            masNode.masonry({
                itemSelector: '.thumbnail',
                isFitWidth: true
            });
        });
    }


    function appendToMasonry() {
        var items = getNewItems().css('opacity', 0);
        processNewItems(items);
        masNode.append(items);

        imagesLoading = true;
        items.imagesLoaded(function () {
            imagesLoading = false;
            items.css('opacity', 1);
            masNode.masonry('appended', items);
        });
    }


    $(window).scroll(function () {
        // alert($(document).scrollTop());
        if ($(document).height() - $(window).height() - $(document).scrollTop() < 10) {

            if (!imagesLoading) {
                appendToMasonry();
            }

        }

    });

});

function AutoResizeImage(maxWidth, maxHeight, objImg) {
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth == 0 && maxHeight == 0) {
        Ratio = 1;
    } else if (maxWidth == 0) {//
        if (hRatio < 1) Ratio = hRatio;
    } else if (maxHeight == 0) {
        if (wRatio < 1) Ratio = wRatio;
    } else if (wRatio < 1 || hRatio < 1) {
        Ratio = (wRatio <= hRatio ? wRatio : hRatio);
    }
    if (Ratio < 1) {
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
}

function upLoad() {
    var fd = new FormData();
    var values = $(".upload>input[name='name']").val();

    // $(".upload>input[name='picId']").removeClass("disappear");

    fd.append("pic", $("#upLoadFile").get(0).files[0]);
    fd.append("name", values);
    $.ajax({
        url: "/upload",
        type: "POST",
        processData: false,
        contentType: false,
        data: fd,
        success: function (obj) {
            if (obj.status) {
                // window.location.reload();           //成功后刷新页面
                alert("上传成功！！！！");
            }
            else {
                alert("图片已经存在！！！！");
            }
            window.location.reload();           //成功后刷新页面
        }
    });
}

function getName() {
    var file = $("#upLoadFile").val();
    var fileName = getFileName(file);
    //alert(file);          //********.jpg

    function getFileName(o) {
        var pos = o.lastIndexOf('\\');
        var pos1 = o.lastIndexOf('.');
        return o.substring(pos + 1, pos1);
    }

    return fileName;
}

/****************************************动态获取input的value值*******************************************/
function getInput() {
    // $(".upload>input[name='name']").attr('value', getName());
    $(".upload>input[name='name']").val(getName());
    $(".upload>input[name='name']").bind('input propertychange', function () {
        $(".upload>input[name='name']").attr("value", $(this).val());
    });
}

getPictureByPage(1, 1000, fillPic);
/****************************************填充图片瀑布流*******************************************/
function fillPic(obj) {
    var picName;
    var picId;
    var picUrl = "";
    if (obj.status) {
        $.each(obj.data.resultList, function (i, item) {
            picName = item.picName;
            picId = item.picCode;
            picUrl = "ID:" + picId + "</br>" + "Title:" + picName;

            $("#masonry_ghost").append('<div class="thumbnail" id="pic' + picId + '">');
            $('div#pic' + picId + '').append('<div class="imgs">');
            $('#pic' + picId + '>.imgs').append('<input type="hidden">');
            $('div#pic' + picId + '').append('<div class="caption">');
            $('#pic' + picId + '>.caption').append('<div class="picCode">');
            $('#pic' + picId + '>.caption>.picCode').append(picUrl);
//                                $('#pic' + i + '>.caption').append('<div class="title">');
//                                $('#pic' + i + '>.caption>.title').append("picTitle:&nbsp;");
            $('#pic' + picId + '>.caption').append('<div class="oper">');
            $('#pic' + picId + '>.caption>.oper').append('<a href="#" class="edit" onclick="edit(this)" id="' + picId + '"></a>');
            $('#pic' + picId + '>.caption>.oper').append('<a href="#" class="del" onclick="firmDel(this)" id="' + picId + '"></a>');
        });
    }
}

$(document).ready(function () {

    $("#upLoadFile").val("");
    $(".upload>input").val("");

});

function getPicName(i) {
    var pic;
    $.ajax({
        url: '../picturelocation?pic_code=' + i,
        async: false,
        dataType: 'json',
        success: function (obj) {
            pic = obj.data.picName;
            //alert(picUrl);

        }
    });
    return pic;
}

/****************************************填充编辑页面*******************************************/
function edit(obj) {
    $(".content").addClass("disappear");
    $(".content_edit").removeClass("appear");
    $(".save,#first").removeClass("disappear");
    $(".submit").addClass("appear");
    var idValue = $(obj).attr("id");
    $("input[name='picId']").val(idValue);
    // alert(getPicName(idValue));
    $("input.input_picName").val(getPicName(idValue));
    $(".upload>input[name='name']").bind('input propertychange', function () {
        $(".upload>input[name='name']").attr("value", $(this).val());
    });
    $("#preview img").attr('src', '../getPic?id=' + idValue);
//                        $(".upload").append("<span>"+getPicName(idValue)+"</span>");
}

/****************************************保存事件*******************************************/
function save() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    //$(".search_text input").attr("value", genJson());
    modifyJSON();
    // window.location.reload();           //成功后刷新页面
}

/****************************************发送JSON到后台*******************************************/
function modifyJSON() {
    var fd = new FormData();
    var picId = $(".upload>input[name='picId']").val();
    var picName = $(".upload>input[name='name']").val();

    fd.append("pic", $("#upLoadFile").get(0).files[0]);
    fd.append("name", picName);
    fd.append("id", picId);
    //alert(eval(fd));
    $.ajax({
        url: "/modifyPic",
        type: "POST",
        processData: false,
        contentType: false,
        data: fd,
        success: function (obj) {
            if(obj.status){
                alert("修改成功！！！！");
                window.location.reload();           //成功后刷新页面
            }
            else{
                alert(obj.message);
            }
        }
    });
}

// /****************************************删除事件*******************************************/
// function del(obj) {
//     var idValue = $(obj).attr("id");
//     subDel();
//     function subDel() {
//         $.getJSON('../deletePic?id=' + idValue, function (obj) {
//             if (obj.errorCode != "0x03") {
//                 alert("删除成功！！！");
//                 window.location.reload();           //成功后刷新页面
//             }
//             else
//                 alert("无权访问！！！");
//         });
//     }
// }

/****************************************确认是否删除事件*******************************************/
function firmDel(obj) {
    //利用对话框返回的值 （true 或者 false）
    if (confirm("你确定删除吗？")) {
        var idValue = $(obj).attr("id");
        // alert(idValue);
        subDel(idValue);
    }
    else {
        // alert("点击了取消");
    }
}

function subDel(idValue) {
    $.getJSON('../deletePic?id=' + idValue, function (obj) {
        if (obj.status) {
            alert("删除成功！！！");
            window.location.reload();           //成功后刷新页面
        }
        else
            alert("无权访问/由于某些神奇的原因删除失败！！！");
    });
}


/****************************************填充编辑页面子方法*******************************************/
function fill(type, idValue) {
    // var name = $("tbody.tableContent tr").eq(idValue).find('#' + type).text();
    // $(".input_" + type).val(name);
    getInput(type);
}


/****************************************提交事件*******************************************/
function submit() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    sentJSON("addPaper");
}


/****************************************取消事件*******************************************/
function cancel() {
    $(".content").removeClass("disappear");
    $(".content_edit").addClass("appear");
    // $(".upload>input[name='picId']").removeClass("disappear");
    $("#preview").html("");
    $("#upLoadFile").val("");
    $(".upload>input").val("");
    $("#preview img").attr('src', '');
    $("#preview").append('<img>');
}

/****************************************添加事件*******************************************/
function add() {
    $(".content,#first").addClass("disappear");
    $(".content_edit").removeClass("appear");
    $(".save").addClass("disappear");
    $(".submit").removeClass("appear");
    // $(".upload>input[name='picId']").addClass("disappear");
}


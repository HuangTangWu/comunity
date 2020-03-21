/**
 * 问题评论
 */
function comment_post() {
    var parentId = $("#questionId-hidden").val();
    var content=$(".content-desc").val();
    var url="/comment";
    doPost(parentId,content,1,url);
}

//二级评论
function second_comment(e){
    var id =$(e).attr("data-id");
    if ($("#comment-"+id).hasClass("in")){
        //关闭二级评论
    }else{
        //打开二级评论
        var url="/comment/"+id;
        if($("#comment-"+id).children().length==1) {
            //未加载过评论
            $.getJSON(url, function (data) {
                var subCommentContainer = $("#comment-" + id);
                $.each(data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object header-pic img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "question-comment"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmt_create).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse in sub-comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                })
            })

        }

    }
    $("#comment-"+id).toggleClass("in");
    $(e).toggleClass("active");
}

//点赞
function like_comment(e) {
    var id =$(e).attr("data-id");
    if ($(e).hasClass("active")){
        //取消点赞
        var url="/like?id="+id+"&num=-1"
        var num=-1;
        doAjax(url,num,id);
    }else{
        var url="/like?id="+id+"&num=1"
        var num=1;
        doAjax(url,num,id);
    }


    $(e).toggleClass("active");
}

function doAjax(url,num,id) {
    $.ajax({
        type:"GET",
        url: url,
        success:function (response) {
            if(response.code==200){
                //点赞成功
                var count=parseInt($("#like-"+id).text());
                count=count+num;
                $("#like-"+id).text(count);
            }else{
                if(response.code==2003){
                    //用户未登录
                    var path=getRootPath();
                    var isAccept = window.confirm(response.message);
                    if(isAccept){
                        var loginPath="https://github.com/login/oauth/authorize?client_id=516d2289a45d5bae20c0&redirect_uri="+path+"/callback&scope=user&state=1";
                        window.open(loginPath);
                        window.localStorage.setItem("close",true);
                    }
                }else{
                    alert(response.message);
                }

            }
        }
    })
}

//二级评论提交
function comment2_post(e) {
    var parentId = e.getAttribute("data-id");
    var content=$(".reply2-"+parentId+" input").val();
    var url="/comment";
    doPost(parentId,content,2,url);
}


//提交评论
function doPost(parentId,content,type,url) {
    var parentId = parentId;
    var content=content;
    if(!content){
        alert("评论不能为空");
        return;
    }
    $.ajax({
        type:"POST",
        url:url,
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":parentId,
            "content":content,
            "type":type
        }),
        success:function (response) {
            if(response.code==200){
                //评论成功
                window.location.reload();
            }else{
                if(response.code==2003){
                    //用户未登录
                    var path=getRootPath();
                    var isAccept = window.confirm(response.message);
                    if(isAccept){
                        var loginPath="https://github.com/login/oauth/authorize?client_id=516d2289a45d5bae20c0&redirect_uri="+path+"/callback&scope=user&state=1";
                        window.open(loginPath);
                        window.localStorage.setItem("close",true);
                    }
                }else{
                    alert(response.message);
                }

            }
        }
    })
}

//选择标签
function  selectTag(e) {
    var tagValue=$("#tag").val();
    var value=$(e).text();
    if(tagValue.indexOf(value)==-1){
        if (tagValue){
            $("#tag").val(tagValue+','+value);
        }else{
            $("#tag").val(value);
        }
    }
}

function tag_click() {
    $(".tab-pane:first").toggleClass("active");
    $(".tags li:first").toggleClass("active");
    $(".tags").toggle();
}

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht);
}

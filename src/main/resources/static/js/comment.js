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
                    var isAccept = window.confirm(response.message);
                    if(isAccept){
                        window.open("https://github.com/login/oauth/authorize?client_id=516d2289a45d5bae20c0&redirect_uri=http://localhost:8087/callback&scope=user&state=1");
                        window.localStorage.setItem("close",true);
                    }
                }else{
                    alert(response.message);
                }

            }
        }
    })
}
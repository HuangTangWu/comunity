package com.zhang.comunity.controller;

import com.zhang.comunity.dto.CommentCreateDTO;
import com.zhang.comunity.dto.CommentDTO;
import com.zhang.comunity.dto.ResultDTO;
import com.zhang.comunity.entity.Comment;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 20:49
 * 评论系统
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    private Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        User u=(User)request.getSession().getAttribute("user");
        if(u==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO==null || commentCreateDTO.getContent()==null || "".equals(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_EMPTY);
        }
        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modify(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(u.getId());
        commentService.insertComment(comment);
        return ResultDTO.okOf();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    private List<CommentDTO> getSecondComment(@PathVariable(name = "id") Integer id){
        //根据id找二级评论的内容
        List<CommentDTO> commentDTOList = commentService.getCommentByQuestionId(id);
        return commentDTOList;
    }

    @GetMapping("/like")
    @ResponseBody
    private Object updateLike(@RequestParam(value = "id") Integer id,
                              @RequestParam(value = "num") Integer num,
                              HttpServletRequest request){
        User u=(User)request.getSession().getAttribute("user");
        if(u==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        int likeCount = commentService.updateLikeCount(id,num);
        if (likeCount==0){
            return ResultDTO.errorOf(2003,"网络异常");
        }
        return ResultDTO.okOf();
    }
}

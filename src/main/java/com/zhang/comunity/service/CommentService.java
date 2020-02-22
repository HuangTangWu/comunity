package com.zhang.comunity.service;

import com.zhang.comunity.dto.CommentDTO;
import com.zhang.comunity.entity.Comment;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.enums.CommentTypeEnum;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.mapper.CommentMapper;
import com.zhang.comunity.mapper.QuestionMapper;
import com.zhang.comunity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/9 15:04
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertComment(Comment comment) {
        if(comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            List<Comment>  commentList=commentMapper.selectCommentById(comment.getParentId());
            if(commentList==null || commentList.size()==0){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertComment(comment);
            //增加评论数
            commentMapper.incCommentCount(Integer.parseInt(String.valueOf(comment.getParentId())));
        }else{
            //回复问题
            Question question = questionMapper.getQuestionById(Integer.parseInt(String.valueOf(comment.getParentId())));
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertComment(comment);
            questionMapper.incCommentCount(Integer.parseInt(String.valueOf(comment.getParentId())));
        }


    }

    public List<CommentDTO> getCommentByQuestionId(Integer id) {
        List<Comment>  commentList = commentMapper.selectCommentByParentId(Long.valueOf(String.valueOf(id)));
        if (commentList==null || commentList.size()==0){
            return new ArrayList<>();
        }
        //collect为所有的评论人id
        Set<Integer> collect = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());


        //所有用户对象
        List<User> userList=new ArrayList<>();

        for (Integer commentId :
                collect) {
            User user = userMapper.getUserById(commentId);
            userList.add(user);
        }
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //返回
        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOList;

    }
}

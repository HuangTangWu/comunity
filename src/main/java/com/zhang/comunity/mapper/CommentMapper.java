package com.zhang.comunity.mapper;

import com.zhang.comunity.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 20:48
 */
@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);

    List<Comment> selectCommentByParentId(Long parentId);

    List<Comment> selectCommentById(Long parentId);

    int incCommentCount(int parseInt);
}

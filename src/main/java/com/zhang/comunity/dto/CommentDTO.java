package com.zhang.comunity.dto;

import com.zhang.comunity.entity.User;
import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 20:41
 * 评论
 */
@Data
public class CommentDTO {
  private Long id;
  private Long parentId;
  private Integer type;
  private Integer commentator;
  private Long gmt_create;
  private Long gmt_modify;
  private Long likeCount;
  private Long commentCount;
  private String content;
  private User user;
}

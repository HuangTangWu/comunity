package com.zhang.comunity.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 20:41
 * 评论
 */
@Data
public class Comment {
  private Long id;
  private Long parentId;
  private Integer type;
  private Integer commentator;
  private Long gmt_create;
  private Long gmt_modify;
  private Long commentCount;
  private Long likeCount;
  private String content;
  public Comment(){}
}

package com.zhang.comunity.dto;

import com.zhang.comunity.entity.User;
import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/16 12:19
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
    private User user;


}

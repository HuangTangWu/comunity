package com.zhang.comunity.entity;

import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/15 17:37
 */
@Data
public class Question {
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



    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modify=" + gmt_modify +
                ", creator=" + creator +
                ", comment_count=" + comment_count +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", tag='" + tag + '\'' +
                '}';
    }
}

package com.zhang.comunity.dto;

import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 20:46
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}

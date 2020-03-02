package com.zhang.comunity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/23 15:59
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}

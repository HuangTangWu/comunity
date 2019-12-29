package com.zhang.comunity.entity;

import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/15 10:15
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmt_create;
    private Long gmt_modify;
    private String avatarUrl;


}

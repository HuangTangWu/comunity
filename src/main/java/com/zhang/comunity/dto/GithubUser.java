package com.zhang.comunity.dto;

import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:29
 */
@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;




    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}

package com.zhang.comunity.dto;

import com.zhang.comunity.entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/18 10:25
 */
@Data
public class UserQuestionDTO {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmt_create;
    private Long gmt_modify;
    private String avatarUrl;
    private List<Question> questionList=new ArrayList<>(0);
}

package com.zhang.comunity.mapper;

import com.zhang.comunity.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/15 18:02
 */
@Mapper
public interface QuestionMapper {
    int addQuestion(Question question);

    List<Question> getAllQuestion();

    int countQuestion();
}

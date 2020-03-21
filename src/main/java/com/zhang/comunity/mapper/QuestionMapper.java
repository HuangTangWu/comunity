package com.zhang.comunity.mapper;

import com.zhang.comunity.dto.UserQuestionDTO;
import com.zhang.comunity.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    UserQuestionDTO getMyQuestion(Integer userId);

    int countMyQuestion(Integer userId);

    List<Question> getMyQuestions(Integer userId);

    Question getQuestionById(Integer id);

    int updateQuestion(Question question);

    void incView(Integer id);

    void incCommentCount(Integer id);

    List<Question> getRelatedQuestionByTag(Map map);

    List<Question> getSearchQuestion(Map<String, String> map);

    List<Question> getHotQuestion();

    List<String> getPopularTags();
}

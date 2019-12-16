package com.zhang.comunity.service;

import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.QuestionMapper;
import com.zhang.comunity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/16 12:20
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> getQuestion(){
        List<Question> questionsList=questionMapper.getAllQuestion();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question :
                questionsList) {
            User u=userMapper.getUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public int countQuestion(){
        return questionMapper.countQuestion();
    }
}

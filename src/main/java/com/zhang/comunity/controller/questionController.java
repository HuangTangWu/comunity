package com.zhang.comunity.controller;

import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/19 11:49
 * 我的问题
 */
@Controller
public class questionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.getQuestionById(id);
        User user = userMapper.getUserById(questionDTO.getCreator());
        questionDTO.setUser(user);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}

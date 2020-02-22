package com.zhang.comunity.controller;

import com.zhang.comunity.dto.CommentDTO;
import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.CommentService;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        questionService.incView(id);
        QuestionDTO questionDTO=questionService.getQuestionById(id);
        model.addAttribute("question",questionDTO);
        List<CommentDTO> commentList= commentService.getCommentByQuestionId(id);
        model.addAttribute("comments",commentList);

        List<Question> questionList=questionService.getRelatedQuestionByTag(questionDTO);
        model.addAttribute("relatedQuestion",questionList);
        return "question";
    }
}

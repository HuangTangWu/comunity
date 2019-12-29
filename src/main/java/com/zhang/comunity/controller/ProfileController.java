package com.zhang.comunity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/17 15:29
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String question(@PathVariable(value = "action") String action, Model model, HttpServletRequest request,
                           @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
                           @RequestParam(value ="pageSize" ,defaultValue = "6") String pageSize){
        User u=(User)request.getSession().getAttribute("user");
        if(u==null){
            return "/";
        }
        // model.addAttribute("user",u);
        if("question".equals(action)){
            model.addAttribute("sectionName","我的问题");
            model.addAttribute("section","question");
        }
        if("reply".equals(action)){
            model.addAttribute("sectionName","我的回复");
        }
        int totalMyQuesiton=questionService.countMyQuestion(u.getId());

        int page_num=Integer.parseInt(pageNum);
        int page_size=Integer.parseInt(pageSize);
        PageHelper.startPage(page_num,page_size);
        List<Question> myQuesitonsList = questionService.getMyQuestions(u.getId());
        PageInfo<Question> pageInfo=new PageInfo<>(myQuesitonsList);

        Pagination pagination=questionService.getPages(totalMyQuesiton,page_num,page_size,pageInfo);

        model.addAttribute("pagination",pagination);
        return "profile";
    }
}

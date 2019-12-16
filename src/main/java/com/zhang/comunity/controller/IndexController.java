package com.zhang.comunity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.QuestionMapper;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
                        @RequestParam(value ="pageSize" ,defaultValue = "5") String pageSize){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    User u=userMapper.getUserByToken(cookie.getValue());
                    request.getSession().setAttribute("user",u);
                    break;
                }
            }
        }

        int total = questionService.countQuestion();
        model.addAttribute("total_question",total);
        int page_Num=Integer.parseInt(pageNum);
        int page_size=Integer.parseInt(pageSize);
        PageHelper.startPage(page_Num,page_size);
        List<QuestionDTO>questionDTOList= questionService.getQuestion();
        PageInfo<QuestionDTO> pageInfo=new PageInfo<>(questionDTOList);
        model.addAttribute("questionList",pageInfo);
        return "index";
    }
}

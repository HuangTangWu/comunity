package com.zhang.comunity.controller;

import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.QuestionMapper;
import com.zhang.comunity.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/15 15:48
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag, Model model, HttpServletRequest request){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("model",model);

        if(title!=null&&"".equals(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description!=null&&"".equals(description)){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if(tag!=null&&"".equals(tag)){
            model.addAttribute("error","标记不能为空");
            return "publish";
        }


        Cookie[] cookies=request.getCookies();
        User u=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                u=userMapper.getUserByToken(cookie.getValue());
                request.getSession().setAttribute("user",u);
                break;
            }
        }
        if(u==null){
            model.addAttribute("error","当前用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setCreator(u.getId());
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modify(question.getGmt_create());

        questionMapper.addQuestion(question);
        return "redirect:/";
    }
}

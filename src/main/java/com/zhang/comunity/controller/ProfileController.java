package com.zhang.comunity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.entity.Notification;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.enums.NotificationStatusEnum;
import com.zhang.comunity.service.NotificationService;
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
 * 侧面展示
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/profile/{action}")
    public String question(@PathVariable(value = "action") String action, Model model, HttpServletRequest request,
                           @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
                           @RequestParam(value ="pageSize" ,defaultValue = "6") String pageSize){
        User u=(User)request.getSession().getAttribute("user");
        if(u==null){
            return "redirect:/";
        }

        int page_num=Integer.parseInt(pageNum);
        int page_size=Integer.parseInt(pageSize);

        if("question".equals(action)){
            model.addAttribute("sectionName","我的问题");
            model.addAttribute("section","question");
            int totalMyQuesiton=questionService.countMyQuestion(u.getId());
            PageHelper.startPage(page_num,page_size);
            List<Question> myQuesitonsList = questionService.getMyQuestions(u.getId());
            PageInfo<Question> pageInfo=new PageInfo<>(myQuesitonsList);

            Pagination pagination=questionService.getPages(totalMyQuesiton,page_num,page_size,pageInfo);
            model.addAttribute("pagination",pagination);
        }
        if("reply".equals(action)){


            model.addAttribute("sectionName","最新回复");
            model.addAttribute("section","reply");
            int countNotify = notificationService.countNotify(Long.valueOf(String.valueOf(u.getId())));
            PageHelper.startPage(page_num,page_size);
            List<Notification> notificationList = notificationService.getNotify(Long.valueOf(String.valueOf(u.getId())));
            PageInfo<Notification> pageInfo=new PageInfo<>(notificationList);
            Pagination pagination=notificationService.getPages(countNotify,page_num,page_size,pageInfo);
            model.addAttribute("pagination",pagination);
        }

        return "profile";
    }

}

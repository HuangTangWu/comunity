package com.zhang.comunity.controller;

import com.mysql.cj.Session;
import com.zhang.comunity.entity.Notification;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.enums.NotificationTypeEnum;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.service.NotificationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/3/2 18:15
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET,value = "/notification/{id}")
    public String notification(HttpServletRequest request, @PathVariable(name="id") Long id){
        User user =(User) request.getSession().getAttribute("user");
        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        Notification notification=notificationService.read(id);
        notificationService.updateNotify(notification);
        if(NotificationTypeEnum.NOTIFY_QUESTION.getType()==notification.getType()
            || NotificationTypeEnum.NOTIFY_COMMENT.getType()==notification.getType()){
            return "redirect:/question/"+notification.getOuterId();
        }
        return "redirect:/";
    }
}

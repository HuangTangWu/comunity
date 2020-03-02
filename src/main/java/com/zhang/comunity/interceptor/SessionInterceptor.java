package com.zhang.comunity.interceptor;

import com.zhang.comunity.entity.User;
import com.zhang.comunity.enums.NotificationStatusEnum;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/18 22:39
 * 拦截器，负责检测当前cookie的令牌,获取user对象放到session中去
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    User u=userMapper.getUserByToken(cookie.getValue());
                    if(u!=null){
                        request.getSession().setAttribute("user",u);
                        //统计未读数目
                        int unReadNum=notificationService.countNotifyByStatus(NotificationStatusEnum.UNREAD.getStatus(),u.getId());
                        request.getSession().setAttribute("unReadNum",unReadNum);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

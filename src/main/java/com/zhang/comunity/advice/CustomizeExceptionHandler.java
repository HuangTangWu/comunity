package com.zhang.comunity.advice;

import com.zhang.comunity.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 11:23
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(CustomizeException.class)
    ModelAndView handle(Throwable ex,Model model) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","服务器冒烟了,请稍后重试");
        }
        return new ModelAndView("error");
    }

}

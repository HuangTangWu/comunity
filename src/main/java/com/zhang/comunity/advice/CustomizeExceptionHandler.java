package com.zhang.comunity.advice;

import com.alibaba.fastjson.JSON;
import com.zhang.comunity.dto.ResultDTO;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 11:23
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(CustomizeException.class)
    Object handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType=request.getContentType();
        if("application/json".equals(contentType)){
            //返回JSON
            ResultDTO resultDTO;
            if(ex instanceof CustomizeException){
                resultDTO= ResultDTO.errorOf((CustomizeException) ex);
            }else{
                resultDTO= ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.println(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {

            }
            return null;
        }else{
            //跳转到错误页面
            if(ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }

}

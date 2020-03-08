package com.zhang.comunity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 * 首页
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
                        @RequestParam(value ="pageSize" ,defaultValue = "7") String pageSize){


        int total = questionService.countQuestion();
        int page_num=Integer.parseInt(pageNum);
        int page_size=Integer.parseInt(pageSize);
        PageHelper.startPage(page_num,page_size);

        List<QuestionDTO>questionDTOList= questionService.getQuestion();
        PageInfo<QuestionDTO> pageInfo=new PageInfo<>(questionDTOList);
        Pagination pagination=questionService.getPages(total,page_num,page_size,pageInfo);

        model.addAttribute("pagination",pagination);
        return "index";
    }
}

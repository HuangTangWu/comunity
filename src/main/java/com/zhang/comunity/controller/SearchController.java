package com.zhang.comunity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 * 首页
 */
@Controller
public class SearchController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/search")
    public String index(Model model,
                        @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
                        @RequestParam(value ="pageSize" ,defaultValue = "7") String pageSize,
                        @RequestParam(value = "searchContent") String searchContent){

        if ("".equals(searchContent) || searchContent==null){
            throw new CustomizeException(CustomizeErrorCode.SEARCH_EMPTY);
        }



        int total = questionService.getSearchQuestion(searchContent).size();
        int page_num=Integer.parseInt(pageNum);
        int page_size=Integer.parseInt(pageSize);
        PageHelper.startPage(page_num,page_size);

        List<QuestionDTO>questionDTOList= questionService.getSearchQuestion(searchContent);
        PageInfo<QuestionDTO> pageInfo=new PageInfo<>(questionDTOList);
        Pagination pagination=questionService.getPages(total,page_num,page_size,pageInfo);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}

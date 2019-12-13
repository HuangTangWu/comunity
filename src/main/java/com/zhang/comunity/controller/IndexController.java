package com.zhang.comunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}

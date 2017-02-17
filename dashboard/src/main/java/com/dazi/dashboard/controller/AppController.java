package com.dazi.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : linguozhi@52tt.com
 * @desc : 应用入口
 * @date :  2017/2/9
 */
@Controller
@RequestMapping("")
public class AppController {
    /**
     * index
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}

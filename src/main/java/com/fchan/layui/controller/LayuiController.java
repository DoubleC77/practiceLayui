package com.fchan.layui.controller;

import com.fchan.layui.service.LayuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("layui")
public class LayuiController {

    @Autowired
    @Qualifier("layuiService")
    private LayuiService layuiService;

    @GetMapping("popQrCode")
    String popQrCode(HttpServletResponse response){
        return layuiService.popQrCode(response,"http://www.baidu.com",300,300);
    }

    @GetMapping("popImg")
    String popImg(HttpServletResponse response){
        return layuiService.popImg(response);
    }

    @PostMapping("testPOst")
    String testPOst(HttpServletResponse response){
        return "redirect:http://www.baidu.com";
    }

}

package com.fchan.layui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/layui")
public class LayuiController {

    @GetMapping("popImageByStream")
    String popImageByStream(){
        return "layuiPopImageByStream";
    }

}

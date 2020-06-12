package com.fchan.layui.viewResolve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/layui")
public class LayuiViewResolveController {

    @GetMapping("popImageByStream")
    String popImageByStream(){
        return "html/layuiPopImageByStream";
    }

    @GetMapping("layuiPopImageByStreamWithLocal")
    String layuiPopImageByStreamWithLocal(){
        return "html/layuiPopImageByStreamWithLocal";
    }

    @GetMapping("layuiPopQRCodeImageByStreamWithLocal")
    String layuiPopQRCodeImageByStreamWithLocal(){
        return "html/layuiPopQRCodeImageByStreamWithLocal";
    }

}

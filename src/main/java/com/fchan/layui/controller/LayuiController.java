package com.fchan.layui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fchan.layui.service.LayuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 1----如果 X-Forwarded-For 获取不到，就去获取X-Real-IP ，X-Real-IP 获取不到，
     * 就依次获取Proxy-Client-IP 、WL-Proxy-Client-IP 、HTTP_CLIENT_IP 、 HTTP_X_FORWARDED_FOR 。
     * 最后获取不到才通过request.getRemoteAddr()获取IP，
     * 2----X-Real-IP 就是记录请求的客户端真实IP。跟X-Forwarded-For 类似。
     * 3----Proxy-Client-IP 顾名思义就是代理客户端的IP，如果客户端真实IP获取不到的时候，就只能获取代理客户端的IP了。
     * 4----WL-Proxy-Client-IP 是在Weblogic下获取真实IP所用的的参数。
     * 5----HTTP_CLIENT_IP 与 HTTP_X_FORWARDED_FOR 可以理解为X-Forwarded-For ， 因为它们是PHP中的用法。
     * @param request
     * @param list
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("testArray")
    @ResponseBody
    String testArray(HttpServletRequest request, @RequestBody List<Integer> list) throws JsonProcessingException {
        //X-Forwarded-For: client, proxy1, proxy2，proxy…
        String ipAddress = request.getHeader("x-forwarded-for");    //squid 服务代理
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");       //apache服务代理
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");    //weblogic 代理
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        final String ip = ipAddress;
        Map result = new HashMap(){{
            put("ip",ip);
        }};
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(result);
    }


    @PostMapping("testGetIp")
    @ResponseBody
    String testGetIp(HttpServletRequest request, @RequestBody List<Integer> list) throws JsonProcessingException {
        String ipAddress = request.getHeader("X-Real-IP");      //nginx代理
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");   //squid 服务代理,多次反向代理后会有多个ip值，第一个ip才是真实ip
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-Forwarded-For");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");    //有些代理
        }


        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X-FORWARDED-FOR");
        }

        final String ip = ipAddress;
        Map<String,String> result = new HashMap(){{
            put("ip",ip);
        }};
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(result);
    }

}

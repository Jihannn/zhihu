package com.huan.zhihu.controller;

import com.huan.zhihu.Interceptor.PassportInterceptor;
import com.huan.zhihu.service.RegLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public  class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    RegLogService regLogService;
    

    @RequestMapping(path = "/reg/",method = {RequestMethod.POST})
    public String register(Model model, @RequestParam("username")String username,
                           @RequestParam("password")String password,
                           HttpServletResponse response){
        try {
            Map<String,String> map = regLogService.register(username,password);
            if(map.containsKey("ticket")){
                    Cookie cookie = new Cookie("ticket",map.get("ticket"));
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return "redirect:/";
                }else {
                    model.addAttribute("msg", map.get("msg"));
                    return "login";
            }
        }catch (Exception e){
            logger.error("注册异常" + e.getMessage());
            return "login";
        }
         /*    return "redirect:/"   放在这里不能传递ticket? */
    }

    @RequestMapping(path = "/login/",method = {RequestMethod.POST})
    public String login(Model model, @RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam(value = "rememberme",defaultValue = "false")boolean rememberme,
                        HttpServletResponse response){

        try {
            Map<String,String> map = regLogService.login(username,password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect/";
            }else {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.error("登录异常" + e.getMessage());
            return "login";
        }

        /*    return "redirect:/"   放在这里不能传递ticket? */
    }

    @RequestMapping(path = "/reglogin",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}

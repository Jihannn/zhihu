package com.huan.zhihu.controller;

import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.User;
import com.huan.zhihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(Model m){
        List<User> user =  userService.select();
        System.out.println(user);
        m.addAttribute("User",user);
        return "index";
    }
}

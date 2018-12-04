package com.huan.zhihu.controller;

import com.huan.zhihu.dao.QuestionDao;
import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.Question;
import com.huan.zhihu.model.User;
import com.huan.zhihu.model.ViewObject;
import com.huan.zhihu.service.QuestionService;
import com.huan.zhihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/","/index"}, method = RequestMethod.GET)
    public String index(Model model){

        List<Question> questionList = questionService.getQuestion(0,0,9);
        List<User> userList = new ArrayList<>();

        for(int i = 1 ; i < 10 ; i++){
            userList.add(userService.getUser(i));
        }

        model.addAttribute("question",questionList);
        model.addAttribute("user",userList);

        return "index";
    }
}

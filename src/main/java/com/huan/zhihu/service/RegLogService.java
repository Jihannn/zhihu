package com.huan.zhihu.service;

import com.huan.zhihu.dao.LoginTicketDao;
import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.LoginTicket;
import com.huan.zhihu.model.User;
import com.huan.zhihu.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RegLogService {
    @Autowired
    UserDao userDao;

    @Autowired
    LoginTicketDao loginTicketDao;


    public Map<String,String> register(String username,String password){
        Map<String,String> msg = new HashMap<>();
        if(StringUtils.isEmpty(username)){
            msg.put("msg","用户名不能为空！");
            return msg;
        }
        if(StringUtils.isEmpty(password)){
            msg.put("msg","密码不能为空！");
            return msg;
        }

        if(userDao.selectUserName(username) != null){
            msg.put("msg","用户名已被注册！");
            return msg;
        }

        User user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassWord(WendaUtil.MD5(password+user.getSalt()));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userDao.addUser(user);

        String ticket = addTicket(user.getId());
        msg.put("ticket",ticket);
        return msg;
    }

    public Map<String,String> login(String username,String password){
        Map<String,String> msg = new HashMap<>();
        if(StringUtils.isEmpty(username)){
            msg.put("msg","用户名不能为空！");
            return msg;
        }
        if(StringUtils.isEmpty(password)){
            msg.put("msg","密码不能为空！");
            return msg;
        }

        if(userDao.selectUserName(username) == null){
            msg.put("msg","用户名不存在！");
            return msg;
        }

        User user = userDao.selectUserName(username);

        String passwordCheck = WendaUtil.MD5(password+user.getSalt());

        if(!user.getPassWord().equals(passwordCheck)){
            msg.put("msg","密码错误！");
            return msg;
        }

        String ticket = addTicket(userDao.selectUserId(username));
        msg.put("ticket",ticket);

        return msg;
    }

    public String addTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setuserId(userId);
        loginTicket.setStatus(0);
        Date now = new Date();
        now.setTime(3600*24 + now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-",""));
        loginTicketDao.addTicket(loginTicket);
        return loginTicket.getTicket();
    }
}

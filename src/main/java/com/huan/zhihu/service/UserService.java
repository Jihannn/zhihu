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
public class UserService {
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

        if(userDao.selectByName(username) != null){
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

        if(userDao.selectByName(username) == null){
            msg.put("msg","用户名不存在！");
            return msg;
        }

        User user = userDao.selectByName(username);

        String passwordCheck = WendaUtil.MD5(password+user.getSalt());

        if(!user.getPassWord().equals(passwordCheck)){
            msg.put("msg","密码错误！");
            return msg;
        }

        String ticket = addTicket(userDao.selectByName(username).getId());
        msg.put("ticket",ticket);

        return msg;
    }

    public String addTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setuserId(userId);
        loginTicket.setStatus(0);
        Date now = new Date();
        now.setTime(now.getTime() + 1000*3600*24);
        loginTicket.setExpired(now);
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-",""));
        loginTicketDao.addTicket(loginTicket);
        return loginTicket.getTicket();
    }

    public void logout(String ticket){
        loginTicketDao.updateStatus(ticket,1);
    }

    public User getUser(int id){
        return userDao.selectById(id);
    }

    public User selectByName(String name) {
        return userDao.selectByName(name);
    }

}

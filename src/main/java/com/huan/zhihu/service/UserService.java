package com.huan.zhihu.service;

import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.User;
import com.huan.zhihu.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService{
    @Autowired
    UserDao userDao;

    public void insert(){
        for(int i = 0 ; i < 10 ; i++){
            User user = new User();
            user.setName("名字"+i);
            user.setPassWord("密码"+i);
            user.setSalt("salt"+i);
            user.setHeadUrl("HeadUrl"+i);
            userDao.addUser(user);
        }
    }
    public List<User> select(){
        List<User> userList = new ArrayList<>();
        for(int i = 1 ; i < 9 ; i++){
            userList.add(userDao.selectbyId(i));
        }
        return userList;
    }public Map<String,String> register(String username, String password){
        Map<String,String> map = new HashMap<>();

        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能为空！");
            return map;
        }
        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空！");
            return map;
        }

        User user =  userDao.selectbyUserName(username);
        if(user != null){
            map.put("msg","用户名已被注册！");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(100)));
        user.setPassWord(WendaUtil.MD5(password+user.getSalt()));
        userDao.addUser(user);

        return map;
    }
}

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

    public User getUser(int id){
        return userDao.selectUser(id);
    }
}

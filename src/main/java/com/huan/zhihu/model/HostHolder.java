package com.huan.zhihu.model;

import com.huan.zhihu.Interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostHolder{
    @Autowired
    PassportInterceptor passportInterceptor;

    private static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser(){
       return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}

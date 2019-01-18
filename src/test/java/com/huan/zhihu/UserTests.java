package com.huan.zhihu;

import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Autowired
    UserDao userDao;

    @Test
    public void demo(){
        //插入测试数据
        User user = new User();
        Random random = new Random();
        for(int i = 1 ; i < 10 ; i++){
            user.setId(i);
            user.setName("User"+i);
            user.setPassWord("Password"+i);
            user.setSalt("");
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
            userDao.addUser(user);
        }

        User a = userDao.selectById(1);
        System.out.print(a.toString());
    }

}

package com.huan.zhihu;

import com.huan.zhihu.dao.QuestionDao;
import com.huan.zhihu.dao.UserDao;
import com.huan.zhihu.model.Question;
import com.huan.zhihu.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTests {
    @Autowired
    QuestionDao questionDao;

    @Test
    public void demo(){
        //插入测试数据
        Question question = new Question();
        Date date = new Date();
        for(int i = 0 ; i < 10; i++) {
            question.setUserId(i);
            question.setTitle("标题" + i);
            question.setContent("这是内容"+i);
            date.setTime(date.getTime() + 1000*3600*i);
            question.setCreatedDate(date);
            question.setCommentCount(i);
            questionDao.addQuestion(question);
        }

    }

}

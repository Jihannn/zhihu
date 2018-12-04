package com.huan.zhihu.service;

import com.huan.zhihu.dao.QuestionDao;
import com.huan.zhihu.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getQuestion(int userId, int offSet, int limit) {
        return questionDao.selectLatestQuestions(userId, offSet, limit);
    }
}

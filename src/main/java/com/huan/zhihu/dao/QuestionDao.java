package com.huan.zhihu.dao;

import com.huan.zhihu.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionDao {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " id,title,content,user_id,created_date,comment_count ";

    @Insert("insert into"+ TABLE_NAME +"("+ INSERT_FIELDS +") values (#{id},#{title},#{content},#{userId},#{createdDate},#{commentCount})")
    void addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);
}

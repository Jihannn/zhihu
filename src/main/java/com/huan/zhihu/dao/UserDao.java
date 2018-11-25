package com.huan.zhihu.dao;

import com.huan.zhihu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name,password,salt,head_url ";
    String SELECT_FIELDS = " id, " +INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME ,"(",INSERT_FIELDS,"" +
            ") values(#{name},#{passWord},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select", SELECT_FIELDS," from ", TABLE_NAME , " where id = #{id}"})
    User selectbyId(int id);

    @Select({"select", SELECT_FIELDS," from ", TABLE_NAME , " where name = #{name}"})
    User selectbyUserName(String name);
}

package com.huan.zhihu.dao;
import com.huan.zhihu.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " id,name,password,salt,head_url ";
    String SELECT_FIELDS = " id " + INSERT_FIELDS;

    @Insert("insert into"+ TABLE_NAME +"(" + INSERT_FIELDS + ") values (#{id},#{name},#{passWord},#{salt},#{headUrl})")
    void addUser(User user);

    @Delete("delete from"+ TABLE_NAME +"where id = #{id}")
    void deleteUser(int id);

    @Update("update"+ TABLE_NAME +"set password = #{password} where id = #{id}")
    void updateUserPassword(User user);

    @Select("select"+ SELECT_FIELDS + "from"+ TABLE_NAME +" where id = #{id}")
    User selectUser(int id);

    @Select("select"+ SELECT_FIELDS + "from"+ TABLE_NAME +"where id = #{id}")
    List<User> getUser(int id);
}

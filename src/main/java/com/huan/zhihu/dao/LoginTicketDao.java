package com.huan.zhihu.dao;

import com.huan.zhihu.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginTicketDao {
    String TABLE_NAME = " login_ticket ";
    String ROW_FIELDS = " id,user_id,ticket,expired,status ";

    @Insert({"insert into",TABLE_NAME,"(",ROW_FIELDS,") values( #{id},#{userId},#{ticket},#{expired},#{status})"})
    void addTicket(LoginTicket loginTicket);

    @Select({"select",ROW_FIELDS,"from",TABLE_NAME,"where ticket = #{ticket}"})
    LoginTicket selectTicket(String ticket);

}

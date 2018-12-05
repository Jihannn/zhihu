package com.huan.zhihu.model;

import java.util.Date;

public class LoginTicket {
    private int id;
    private int userId;
    private int status;
    private String ticket;
    private Date expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int user_id) {
        this.userId = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}

package com.nowcoder.community.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (LoginTicket)实体类 登陆凭证记录
 *
 * @author Ss
 */

public class LoginTicket implements Serializable {
    private static final long serialVersionUID = 438342979500803230L;
    
    private int id;
    
    private int userId;
    
    private String ticket;
    /**
    * 0-有效; 1-无效;
    */
    private Integer status;


    /**
     * 到期的时间
     */
    private Date expired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

}
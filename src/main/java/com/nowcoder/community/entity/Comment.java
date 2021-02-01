package com.nowcoder.community.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 *
 *
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -66137005452989538L;
    
    private Integer id;

    //评论的人的id
    private Integer userId;

    //评论的类型 是评论帖子 还是评论回复
    private Integer entityType;

    //评论的这个东西（帖子或者回复）的id
    private Integer entityId;

    //评论所指向的用户的id
    private Integer targetId;

    //评论的内容
    private String content;

    //此评论的状态 是正常的还是已经删除的
    private Integer status;

    //评论创建的时间
    private Date createTime;
}
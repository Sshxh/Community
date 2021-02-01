package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 根据评论对象实体分页查询评论列表
     *
     * @param entityType 评论对象，用户或者帖子
     * @param entityId   评论对象 id
     * @param offset
     * @param limit
     * @return
     */
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    /**
     * 查询评论条目数
     *
     * @param entityType
     * @param entityId
     * @return
     */
    Integer selectCountByEntity(int entityType, int entityId);

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);







}

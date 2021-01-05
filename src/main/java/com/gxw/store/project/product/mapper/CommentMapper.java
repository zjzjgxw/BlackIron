package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.dto.CommentSearchParams;
import com.gxw.store.project.product.entity.Comment;

import java.util.List;

public interface CommentMapper {
    void create(Comment comment);

    List<Comment> getComments(CommentSearchParams params);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int delete(Long id);
}

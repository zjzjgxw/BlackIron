package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.Comment;

import java.util.List;

public interface CommentMapper {
    void create(Comment comment);

    List<Comment> getComments(Long businessId, Long productId, Long orderId);


    /**
     * 删除评论
     * @param id
     * @return
     */
    int delete(Long id);
}

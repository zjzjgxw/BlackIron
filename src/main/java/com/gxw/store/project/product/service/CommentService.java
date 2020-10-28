package com.gxw.store.project.product.service;


import com.gxw.store.project.product.entity.Comment;

import java.util.List;


public interface CommentService {
    Long create(Comment comment);

    List<Comment> getComments(Long businessId, Long productId, Long orderId);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Boolean delete(Long id);
}

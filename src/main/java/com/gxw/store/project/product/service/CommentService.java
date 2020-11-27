package com.gxw.store.project.product.service;


import com.gxw.store.project.product.entity.Comment;

import java.util.List;


public interface CommentService {
    boolean create(Comment comment);

    boolean create(List<Comment> comments);

    List<Comment> getComments(Long businessId, Long productId, Long orderId);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 获取评论
     * @param businessId
     * @param productId
     * @return
     */
    List<Comment> getComments(Long businessId, Long productId);
}

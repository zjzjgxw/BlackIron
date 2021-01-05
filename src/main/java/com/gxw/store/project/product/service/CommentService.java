package com.gxw.store.project.product.service;


import com.gxw.store.project.product.dto.CommentSearchParams;
import com.gxw.store.project.product.entity.Comment;

import java.util.List;


public interface CommentService {
    boolean create(Comment comment);

    boolean create(List<Comment> comments);

    List<Comment> getComments(CommentSearchParams params);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Boolean delete(Long id);

}

package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.product.entity.Comment;
import com.gxw.store.project.product.mapper.CommentMapper;
import com.gxw.store.project.product.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentServiceImp implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Long create(Comment comment) {
        commentMapper.create(comment);
        return comment.getId();
    }
}

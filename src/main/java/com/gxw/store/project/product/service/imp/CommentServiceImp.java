package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.order.service.OrderService;
import com.gxw.store.project.product.entity.Comment;
import com.gxw.store.project.product.mapper.CommentMapper;
import com.gxw.store.project.product.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private OrderService orderService;

    @Override
    @Transactional
    public Long create(Comment comment) {
        commentMapper.create(comment);
        //TODO 更改订单状态，这里先通过方法调用实现， 好的做法是使用消息队列进行一个解耦
        orderService.finished(comment.getOrderId(), comment.getBusinessId());
        return comment.getId();
    }

    @Override
    public List<Comment> getComments(Long businessId, Long productId, Long orderId) {
        return commentMapper.getComments(businessId, productId, orderId);
    }

    @Override
    public Boolean delete(Long id) {
        int row = commentMapper.delete(id);
        return row != 0;
    }
}

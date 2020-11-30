package com.gxw.store.project.product.service.imp;

import ch.qos.logback.core.util.FileUtil;
import com.gxw.store.project.common.utils.FileUtils;
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
    public boolean create(Comment comment) {
        commentMapper.create(comment);
        //TODO 更改订单状态，这里先通过方法调用实现， 好的做法是使用消息队列进行一个解耦
        orderService.finished(comment.getOrderId(), comment.getBusinessId());
        return true;
    }

    @Override
    @Transactional
    public boolean create(List<Comment> comments) {
        Long orderId = 0L;
        Long businessId = 0L;
        for (Comment comment: comments){
            commentMapper.create(comment);
            orderId = comment.getOrderId();
            businessId = comment.getBusinessId();
        }
        orderService.finished(orderId,businessId);
        return true;
    }

    @Override
    public List<Comment> getComments(Long businessId, Long productId, Long orderId) {
        List<Comment> comments = commentMapper.getComments(businessId, productId, orderId);
        return handleComments(comments);
    }

    @Override
    public Boolean delete(Long id) {
        int row = commentMapper.delete(id);
        return row != 0;
    }

    @Override
    public List<Comment> getComments(Long businessId, Long productId) {
        List<Comment> comments = commentMapper.getComments(businessId, productId, null);
        return handleComments(comments);
    }

    private List<Comment> handleComments(List<Comment> comments){
        for (Comment comment : comments){
            if(!FileUtils.isFullPath(comment.getCoverUrl())){
                comment.setCoverUrl(FileUtils.getPath(comment.getCoverUrl()));
            }
            if(!comment.getImgUrl().isEmpty()){
                comment.setImgUrl(FileUtils.getPath(comment.getImgUrl()));
            }
        }
        return  comments;
    }
}

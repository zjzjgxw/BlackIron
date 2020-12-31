package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.order.service.OrderService;
import com.gxw.store.project.product.entity.Comment;
import com.gxw.store.project.product.service.CommentService;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("app/products/comments")
public class AppCommentController extends BaseController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    @NeedToken
    @PostMapping
    public ResponseResult create(@Valid @RequestBody Comment comment) {
        comment.setUserId(SessionUtils.getUserId());
        //获取用户信息
        User user = userService.selectUserById(comment.getUserId());
        if(user == null){
            throw new NotExistException("用户不存在");
        }
        comment.setUserName(user.getName());
        comment.setCoverUrl(user.getProfileUrl());

        Boolean hasOwnOrder = orderService.hasOrderOfUser(comment.getOrderId(),comment.getProductId(),comment.getUserId());
        if(!hasOwnOrder){
            throw new NotExistException("不存在相应的评论订单");
        }

        if(commentService.create(comment)){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @NeedToken
    @PostMapping("/multi")
    public ResponseResult createComments(@RequestBody List<Comment> comments) {
        //获取用户信息
        User user = userService.selectUserById(SessionUtils.getUserId());
        if(user == null){
            throw new NotExistException("用户不存在");
        }
        for(Comment comment: comments){
            comment.setUserId(SessionUtils.getUserId());
            comment.setBusinessId(SessionUtils.getBusinessId());
            Boolean hasOwnOrder = orderService.hasOrderOfUser(comment.getOrderId(),comment.getProductId(),comment.getUserId());
            if(!hasOwnOrder){
                throw new NotExistException("不存在相应的评论订单");
            }

            comment.setUserName(user.getName());
            comment.setCoverUrl(user.getProfileUrl());
        }

        if(commentService.create(comments)){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @GetMapping
    public ResponseResult getComments(@RequestParam Long businessId, @RequestParam(required = false) Long productId) {
        startPage();
        List<Comment> comments = commentService.getComments(businessId,productId);
        return ResponseResult.success(getDataTable(comments));
    }
}

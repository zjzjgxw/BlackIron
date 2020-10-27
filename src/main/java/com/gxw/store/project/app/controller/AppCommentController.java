package com.gxw.store.project.app.controller;


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
import java.util.HashMap;


@RestController
@RequestMapping("app/products/comments")
public class AppCommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

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

        Long id = commentService.create(comment);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }


}

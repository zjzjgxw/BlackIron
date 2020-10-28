package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
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
import java.util.List;


@RestController
@RequestMapping("/products/comments")
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;


    @GetMapping
    public ResponseResult getComments(@RequestParam Long businessId, @RequestParam(required = false) Long productId,
                                      @RequestParam(required = false) Long orderId) {
        startPage();
        List<Comment> comments = commentService.getComments(businessId,productId,orderId);
        return ResponseResult.success(getDataTable(comments));
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        boolean success = commentService.delete(id);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}

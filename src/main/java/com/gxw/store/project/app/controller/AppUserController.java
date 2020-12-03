package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("app/users")
public class AppUserController extends BaseController {

    @Autowired
    private UserService userService;


    @PutMapping()
    public ResponseResult update(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @GetMapping("/current")
    public ResponseResult current(){
        User user = userService.selectUserById(SessionUtils.getUserId());
        HashMap<String, User> res = new HashMap<>();
        res.put("user", user);
        return ResponseResult.success(res);
    }



}

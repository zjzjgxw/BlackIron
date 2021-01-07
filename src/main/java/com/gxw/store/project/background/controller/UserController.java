package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.dto.FollowRel;
import com.gxw.store.project.user.dto.UserSearchParams;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Validated @RequestBody User user) {
        Long id = userService.create(user);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success("创建成功", res);
    }

    @NeedToken
    @PutMapping()
    public ResponseResult update(@Valid @RequestBody User user) {
        user.setBusinessId(SessionUtils.getBusinessId());
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @NeedToken
    @PatchMapping("/{id}")
    public ResponseResult changeStatus(@PathVariable Long id) {
        if (userService.changeUserStatus(id)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getUsers(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String account,
                                   @RequestParam(required = false) String tel,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
                                   @RequestParam(required = false) Long vipId,
                                   @RequestParam(required = false) List<Integer> status) {
        startPage();
        List<User> users = null;
        UserSearchParams searchParams = new UserSearchParams(id, SessionUtils.getBusinessId(), account, tel, name, email, birthday, vipId, status);
        users = userService.getUsers(searchParams);
        return ResponseResult.success(getDataTable(users));
    }

    @NeedToken
    @GetMapping("/{id}")
    public ResponseResult selectUserById(@PathVariable Long id) {
        HashMap<String, User> res = new HashMap<>();
        res.put("user", userService.selectUserById(id));
        return ResponseResult.success(res);
    }


    @NeedToken
    @GetMapping("/account")
    public ResponseResult getUserByAccount(@RequestParam String account) {
        HashMap<String, User> res = new HashMap<>();
        res.put("user", userService.selectUserByAccount(account));
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/phone")
    public ResponseResult getUserByPhone(@RequestParam String phone) {
        HashMap<String, User> res = new HashMap<>();
        res.put("user", userService.selectUserByPhone(phone));
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/openId")
    public ResponseResult selectUserByOpenId(@RequestParam("openId") String openId, @RequestParam("canCreate") Boolean canCreate) {
        HashMap<String, User> res = new HashMap<>();
        res.put("user", userService.selectUserByOpenId(openId, canCreate, SessionUtils.getBusinessId()));
        return ResponseResult.success(res);
    }

    /**
     * 关注
     *
     * @param followRel
     * @return
     */
    @NeedToken
    @PutMapping("/follows")
    public ResponseResult follow(@Valid @RequestBody FollowRel followRel) {
        for (Long id : followRel.getIdolIds()) {
            if (followRel.getUserId().equals(id)) {
                return ResponseResult.error("不能关注自己");
            }
        }

        Boolean success = userService.follow(followRel.getUserId(), followRel.getIdolIds());
        HashMap<String, Boolean> res = new HashMap<>();
        res.put("success", success);
        return ResponseResult.success(res);
    }

    /**
     * 取消关注
     *
     * @param userId
     * @param idolId
     * @return
     */
    @NeedToken
    @DeleteMapping("/follows")
    public ResponseResult cancelFollow(@RequestParam Long userId, @RequestParam Long idolId) {
        Boolean success = userService.cancelFollow(userId, idolId);
        HashMap<String, Boolean> res = new HashMap<>();
        res.put("success", success);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/follows")
    public ResponseResult getIdols(@RequestParam Long userId) {
        startPage();
        List<User> users = userService.getIdols(userId);
        return ResponseResult.success(getDataTable(users));
    }

    @NeedToken
    @GetMapping("/fans")
    public ResponseResult getFans(@RequestParam Long userId) {
        startPage();
        List<User> users = userService.getFans(userId);
        return ResponseResult.success(getDataTable(users));
    }

}

package com.gxw.store.project.user.service.imp;

import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.common.utils.exception.InvalidUserException;
import com.gxw.store.project.user.dto.UserSearchParams;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.mapper.UserMapper;
import com.gxw.store.project.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long create(User user) {
        User tmp = selectUserByAccount(user.getAccount());
        if (tmp != null) {
            throw new HasExistException("账号已经存在");
        }

        user.setPassword(Md5Utils.hash(user.getPassword()));
        userMapper.create(user);
        return user.getId();
    }


    @Override
    public User selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public boolean updateUser(User user) {
        userMapper.updateUser(user);
        return true;
    }

    @Override
    public List<User> getUsers(UserSearchParams searchParams) {
        return userMapper.getUsers(searchParams);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByOpenId(String openId, boolean canCreate) {
        if (canCreate) { //运行不存在就创建一个新用户
            User user = userMapper.selectUserByOpenId(openId);
            if(user == null){
                user = new User();
                user.setName("WX_USER");
                user.setPassword("123456");
                user.setAccount(openId);
                user.setTel(RandomStringUtils.randomNumeric(20));
                user.setOpenId(openId);
                user.setEmail("");
                user.setProfileUrl("");
                userMapper.create(user);
            }
            return user;
        } else {
            return userMapper.selectUserByOpenId(openId);
        }
    }


    @Override
    @Transactional
    public boolean follow(Long userId, Long[] idols) {
        int userNums = userMapper.getNormalUserNum(idols);
        if (userNums != idols.length) {
            throw new InvalidUserException("存在非正常用户");
        }
        int followedNum = userMapper.hasFollowed(userId, idols);
        if (followedNum > 0) {
            throw new HasExistException("存在已经关注的用户");
        }
        userMapper.addIdols(userId, idols); //为用户增加 N个关注者
        userMapper.addFans(idols, userId); //为N个关注者增加 一个粉丝
        userMapper.updateIdolNum(userId, idols.length); //为用户更新关注者数量
        userMapper.updateFansNum(idols, 1);//为N个关注者增加 1个粉丝数量
        return true;
    }

    @Override
    @Transactional
    public boolean cancelFollow(Long userId, Long idolId) {
        int followedNum = userMapper.hasFollowed(userId, new Long[]{idolId});
        if (followedNum > 0) {
            userMapper.deleteIdol(userId, idolId);
            userMapper.deleteFans(idolId, userId);
            userMapper.updateFansNum(new Long[]{idolId}, -1);
            userMapper.updateIdolNum(userId, -1);
        }
        return true;
    }

    @Override
    public List<User> getIdols(Long userId) {
        return userMapper.getIdols(userId);
    }

    @Override
    public List<User> getFans(Long userId) {
        return userMapper.getFans(userId);
    }

}
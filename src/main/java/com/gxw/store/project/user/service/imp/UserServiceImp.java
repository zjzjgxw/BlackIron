package com.gxw.store.project.user.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.WeiXinUtils;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.common.utils.exception.InvalidUserException;
import com.gxw.store.project.user.dto.UserSearchParams;
import com.gxw.store.project.user.dto.WxEncryptedData;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.entity.VipInfo;
import com.gxw.store.project.user.mapper.UserMapper;
import com.gxw.store.project.user.service.UserService;
import com.gxw.store.project.user.service.VipService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private VipService vipService;

    @Resource
    private WeiXinUtils weiXinUtils;

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
    public boolean changeUserStatus(Long id) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            return false;
        }
        int status = user.getStatus();
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        user.setStatus(status);
        int row = userMapper.updateUser(user);
        return row > 0;
    }

    @Override
    public List<User> getUsers(UserSearchParams searchParams) {
        List<User> users = userMapper.getUsers(searchParams);
        List<VipInfo> vipInfos = vipService.getVips(searchParams.getBusinessId());
        if (vipInfos != null && !vipInfos.isEmpty()) {
            for (User user : users) {
                VipInfo vipInfo = vipService.getCurrentVipInfo(user.getConsumePrice(), vipInfos);
                user.setVip(vipInfo);
            }
        }

        return users;
    }

    @Override
    public boolean updateWxUser(Long userId, String sessionKey, WxEncryptedData wxEncryptedData) {
        JSONObject object = weiXinUtils.getUserInfo(wxEncryptedData.getEncryptedData(), sessionKey, wxEncryptedData.getIv());
        User user = userMapper.selectUserById(userId);
        user.setName((String) object.get("nickName"));
        user.setProfileUrl((String) object.get("avatarUrl"));
        if (object.containsKey("unionId")) {
            user.setUnionId((String) object.get("unionId"));
        }
        this.updateUser(user);
        return true;
    }

    @Override
    public User selectUserById(Long id) {
        User user = userMapper.selectUserById(id);
        List<VipInfo> vipInfos = vipService.getVips(user.getBusinessId());
        if (vipInfos != null && !vipInfos.isEmpty()) {
            VipInfo vipInfo = vipService.getCurrentVipInfo(user.getConsumePrice(), vipInfos);
            user.setVip(vipInfo);
        }
        return user;
    }

    @Override
    public User selectUserByOpenId(String openId, boolean canCreate, Long businessId) {
        if (canCreate) { //运行不存在就创建一个新用户
            User user = userMapper.selectUserByOpenId(openId);
            if (user == null) {
                user = new User();
                user.setName("WX_USER");
                user.setBusinessId(businessId);
                user.setPassword("123456");
                user.setAccount(openId);
                user.setTel(RandomStringUtils.randomNumeric(20));
                user.setOpenId(openId);
                user.setEmail("");
                user.setProfileUrl("");
                user.setVipFlag(0L);
                user.setVipId(0L);
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

    @Override
    public Boolean addConsumePrice(Long id, Long price) {
        int row = userMapper.addConsumePrice(id, price);
        return row != 0;
    }

    @Override
    public Boolean addPoint(Long id, Long point) {
        int row = userMapper.addPoint(id, point);
        return row != 0;
    }

    @Override
    public Boolean updateVip(Long id) {
        User user = userMapper.selectUserById(id);
        List<VipInfo> vipInfos = vipService.getVips(user.getBusinessId());
        if (vipInfos == null || vipInfos.isEmpty()) {
            return false;
        }
        VipInfo vipInfo = vipService.getCurrentVipInfo(user.getConsumePrice(), vipInfos);
        if (vipInfo != null) {
            user.setVipId(vipInfo.getId());
            userMapper.updateUser(user);
        }
        return true;
    }

    @Override
    public void freshAllUserVip(Long businessId) {
        List<VipInfo> vipInfos = vipService.getVips(businessId);
        if (vipInfos == null || vipInfos.isEmpty()) {
            return;
        }
        int pageNum = 1;
        int pageSize = 20;
        while (true) {
            PageHelper.startPage(pageNum, pageSize);
            List<User> users = userMapper.getUsers(null);
            if (users == null || users.size() < pageSize) {
                break;
            }
            //TODO 可以考虑批量更新
            for (User user : users) {
                VipInfo vipInfo = vipService.getCurrentVipInfo(user.getConsumePrice(), vipInfos);
                if (vipInfo != null) {
                    user.setVipId(vipInfo.getId());
                    userMapper.updateUser(user);
                }
            }
            pageNum += 1;
        }
    }


}

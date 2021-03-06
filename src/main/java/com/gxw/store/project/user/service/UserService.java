package com.gxw.store.project.user.service;


import com.gxw.store.project.product.dto.AccessStat;
import com.gxw.store.project.user.dto.UserSearchParams;
import com.gxw.store.project.user.dto.WxEncryptedData;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.entity.UserStat;

import java.util.Date;
import java.util.List;


public interface UserService {
    Long create(User user);

    User selectUserByAccount(String account);

    User selectUserByPhone(String phone);

    boolean updateUser(User user);

    /**
     * 记录登录信息
     * @param userId
     * @param ip
     * @return
     */
    boolean recordLogin(Long userId,String ip);

    /**
     * 修改用户状态
     * @param id
     * @return
     */
    boolean changeUserStatus(Long id);

    List<User> getUsers(UserSearchParams searchParams);

    /**
     * 更新用户信息
     * @param userId
     * @param sessionKey
     * @param wxEncryptedData
     * @return
     */
    boolean updateWxUser(Long userId, String sessionKey, WxEncryptedData wxEncryptedData);

    /**
     * 根据id搜索用户
     * @param id
     * @return
     */
    User selectUserById(Long id);

    /**
     * 根据openId查找用户
     * @param openId
     * @return
     */
    User selectUserByOpenId(String openId, boolean canCreate, Long businessId);

    /**
     * 关注 某些人
     * @param userId
     * @param idols
     * @return
     */
    boolean follow(Long userId, Long[] idols);

    /**
     * 取关
     * @param userId
     * @param idolId
     * @return
     */
     boolean cancelFollow(Long userId, Long idolId);


    /**
     * 获取用户的关注者
     * @param userId
     * @return
     */
    List<User> getIdols(Long userId);

    /**
     * 获取用户的粉丝
     * @param userId
     * @return
     */
    List<User> getFans(Long userId);

    /**
     * 增加消费金额
     * @param id 用户id
     * @param price
     * @return
     */
    Boolean addConsumePrice(Long id, Long price);

    /**
     * 增加消费金额
     * @param id 用户id
     * @param point
     * @return
     */
    Boolean addPoint(Long id, Long point);


    Boolean updateVip(Long id);


    /**
     * 刷新所有用户的vip等级信息
     */
    void freshAllUserVip(Long businessId);

    /**
     * 获取新增用户数
     * @param businessId
     * @param startTime
     * @param endTime
     * @return
     */
    List<UserStat>  newUserStatDateRange(Long businessId, Date startTime, Date endTime);

}

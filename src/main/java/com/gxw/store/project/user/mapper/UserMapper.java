package com.gxw.store.project.user.mapper;


import com.gxw.store.project.user.dto.UserSearchParams;
import com.gxw.store.project.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
     void create(User user);
     User selectUserByAccount(String account);
     User selectUserByPhone(String phone);
     User selectUserByEmail(String email);
     User selectUserById(Long id);

     List<User> getUsers(UserSearchParams searchParams);

     int updateUser(User user);

     /**
      * 根据用户openId 进行查找
      * @param openId
      * @return
      */
     User selectUserByOpenId(String openId);

     /**
      * 获取正常的用户数量
      * @param userIds
      * @return
      */
     int getNormalUserNum(Long[] userIds);

     /**
      * 更新关注者数量
      * @param userId
      * @param nums
      */
     int updateIdolNum(Long userId, int nums);

     /**
      * 更新粉丝数量
      * @param userIds
      * @param nums
      * @return
      */
     int updateFansNum(@Param("userIds") Long[] userIds, int nums);

     /**
      * 判断用户是否已经关注
      * @param userId
      * @param idols
      * @return
      */
     int hasFollowed(Long userId, Long[] idols);

     /**
      * 新增关注者
      * @param userId 关注人id
      * @param idols  被关注人id
      * @return
      */
     int addIdols(Long userId, Long[] idols);


     /**
      * 新增粉丝
      * @param userIds
      * @param fansId  粉丝id
      * @return
      */
     int addFans(Long[] userIds, Long fansId);

     /**
      * 取关
      * @param userId
      * @param idolId
      * @return
      */
     int deleteIdol(Long userId, Long idolId);

     /**
      * 减少粉丝
      * @param userId
      * @param fansId
      * @return
      */
     int deleteFans(Long userId, Long fansId);

     /**
      * 获取某个用户的关注者列表
      * @param userId
      * @return
      */
     List<User> getIdols(Long userId);

     /**
      * 获取某个用户的粉丝列表
      * @param userId
      * @return
      */
     List<User> getFans(Long userId);

     /**
      * 为用户增加消费金额
      * @param id
      * @param price
      * @return
      */
     int addConsumePrice(Long id, Long price);
}

package com.lsh.sp.service;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.pojo.vo.ResetUser;


/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:33 2022/4/9
 **/
public interface UserService {
    ResponseResult userInfo(Long id);
    ResponseResult updateUserProfile(User user);
    ResponseResult createUser(User user);
    ResponseResult updatePassword(ResetUser resetUser);

    ResponseResult selectAllUser();
}

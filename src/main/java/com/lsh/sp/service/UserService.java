package com.lsh.sp.service;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:33 2022/4/9
 **/
public interface UserService {
    ResponseResult userInfo(Long id);
    ResponseResult updateUserProfile(User user);
}

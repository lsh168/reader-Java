package com.lsh.sp.service;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 21:52 2022/4/7
 **/
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();


}

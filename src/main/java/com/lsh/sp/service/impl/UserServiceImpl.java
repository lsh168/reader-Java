package com.lsh.sp.service.impl;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.mapper.UserMapper;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:35 2022/4/9
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult userInfo(Long id) {

        User user = userMapper.selectById(id);
        return new ResponseResult(200,"请求成功",user);
    }

    @Override
    public ResponseResult updateUserProfile(User user) {
        userMapper.updateById(user);
        return new ResponseResult(200,"局部更新成功！",user);
    }
}

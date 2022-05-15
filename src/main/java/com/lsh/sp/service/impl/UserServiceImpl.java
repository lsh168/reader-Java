package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.mapper.UserMapper;
import com.lsh.sp.pojo.vo.ResetUser;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public ResponseResult createUser(User user) {

        if (user.getUserName()!=null
//                && user.getNickName()!=null
                &&user.getPassword()!=null){
            String encode = passwordEncoder().encode(user.getPassword());
            user.setPassword(encode);
            userMapper.insert(user);

        }
        else throw new RuntimeException("用户信息非法！");
        return new ResponseResult(200,"success");
    }

    @Override
    public ResponseResult updatePassword(ResetUser resetUser) {
//        加密后的旧密码
        String oldPass = passwordEncoder().encode(resetUser.getPassword());
        String newPass = passwordEncoder().encode(resetUser.getNewPassword());
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", resetUser.getUserName()));
        //查询用户是否存在
        if (user!=null){
//            if (user.getPassword().equals(oldPass)){
                user.setPassword(newPass);
                userMapper.updateById(user);
//            }
        }
        else new RuntimeException("用户不存在！");
        return new ResponseResult(200,"success");
    }

    @Override
    public ResponseResult selectAllUser() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        return new ResponseResult(200,"success",users);
    }

}

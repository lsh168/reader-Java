package com.lsh.sp.service.impl;

import com.lsh.sp.domain.LoginUser;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.service.LoginService;
import com.lsh.sp.utils.JwtUtil;
import com.lsh.sp.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 21:52 2022/4/7
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //authenticationManager  authenticate进行用户认证
        //user.getUserName() 认证主体
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        String kaptchaVerifyCode = redisCache.getCacheObject("kaptchaVerifyCode");
//        redisCache.deleteObject("kaptchaVerifyCode");
//        DIY
//        if (!kaptchaVerifyCode.equals(vc))
//            throw new RuntimeException("验证码错误！");

        //如果认证没通过，给出提示
        if (Objects.isNull(authenticate))
            throw new RuntimeException("登陆失败！");
        //如果通过认证，使用UserId生成JWT
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        //将完整的用户信息存入redis，userId作为Key
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取securityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }
}

package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:32 2022/4/9
 **/
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("info")
    public ResponseResult userInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(token);
        //解析token
        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.parseLong(claims.getSubject());
            log.info("UserId={}",userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        return userService.userInfo(userId);
    }
    @PatchMapping("updateUserProfile")
    public ResponseResult updateUserProfile(HttpServletRequest request,User user){
        String token = request.getHeader("token");
        System.out.println(token);
        //解析token
        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.parseLong(claims.getSubject());
            log.info("UserId={}",userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        user.setId(userId);
        log.info("user={}",user);

        return userService.updateUserProfile(user);
    }
}

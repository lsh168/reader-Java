package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.pojo.vo.ResetUser;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("createUser")
    public ResponseResult createUser(User user){
        ResponseResult responseResult = userService.createUser(user);
        return responseResult;
    }
    @PostMapping("forget")
    public ResponseResult forget(ResetUser resetUser){
//        @RequestParam("userName")String userName,@RequestParam("password") String password,@RequestParam("newPassword") String newPassword
//        @RequestBody ResetUser resetUser
//        ResetUser resetUser=new ResetUser();
//        User user=new User();
//        user.setUserName(userName);
//        user.setPassword(password);
//        resetUser.setNewPassword(newPassword);
//        resetUser.setUser(user);
        return userService.updatePassword(resetUser);
    }

    @PostMapping("findAllUser")
    public ResponseResult findAllUser(){
        return userService.selectAllUser();
    }
}

package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 21:49 2022/4/7
 **/
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")  //@RequestBody
    public ResponseResult login(User user){
        //登录
        return loginService.login(user);
    }
    @PostMapping("logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}

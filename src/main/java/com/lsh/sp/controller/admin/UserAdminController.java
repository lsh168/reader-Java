package com.lsh.sp.controller.admin;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 13:34 2022/4/30
 **/
@RestController
@RequestMapping("userAdmin")
public class UserAdminController {

    @Autowired
    private UserService userService;
    @PostMapping("update")
    public ResponseResult updateUser(User user){
        return userService.updateUserProfile(user);

    }
}

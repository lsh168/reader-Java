package com.lsh.sp.service.impl;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.pojo.vo.ResetUser;
import com.lsh.sp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 14:10 2022/4/28
 **/
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    void test(){
        User user=new User();
        user.setId(1519563589327831041L);
//        user.setNickName("haha");
        user.setPassword("123");

        user.setUserName("lshll");
//        ResponseResult user1 = userService.createUser(user);
//        System.out.println(user1);
//        ResetUser resetUser=new ResetUser();
//        resetUser.setUser(user);
//        resetUser.setNewPassword("12321");
//
//        ResponseResult responseResult = userService.updatePassword(resetUser);
//        System.out.println(responseResult);

    }

}
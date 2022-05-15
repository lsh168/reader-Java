package com.lsh.sp.service.impl;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:51 2022/4/16
 **/
@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;
    @Test
    void selectReadStateByMap() {
        ResponseResult responseResult = memberService.selectReadStateByMap((long) 1);
        System.out.println(responseResult);

    }

    @Test
    void updateStatus() {
//        Member member=new Member();
//        member.setAvatar("http://localhost:8866/images/2.png");
//        ResponseResult responseResult = memberService.updateStatus(member);
//        System.out.println(responseResult);
    }
}
package com.lsh.sp.controller.admin;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:33 2022/5/5
 **/
@RestController
@RequestMapping("memberAdmin")
public class MemberAdminController {
    @Autowired
    private MemberService memberService;
    @PostMapping("findAll")
    public ResponseResult findAllMember(){
        ResponseResult result = memberService.findAllMember();
        return result;
    }
}

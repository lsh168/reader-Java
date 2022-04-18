package com.lsh.sp.mapper;

import com.lsh.sp.pojo.vo.UserReadState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:17 2022/4/16
 **/
@SpringBootTest
class MemberReadStateMapperTest {
    @Autowired
    private MemberReadStateMapper memberReadStateMapper;
    @Test
    void selectReadState(){
        UserReadState userReadState = memberReadStateMapper.selectReadStateByMap((long) 1, 1);
        System.out.println(userReadState);

    }

}
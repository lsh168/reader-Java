package com.lsh.sp.service.impl;

import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.pojo.vo.ScoreStatistics;
import com.lsh.sp.service.BookService;
import com.lsh.sp.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 12:39 2022/4/16
 **/
@SpringBootTest
class EvaluationServiceImplTest {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private BookService bookService;

    @Test
    void test() {
        Member member=new Member();
        member.setMemberId((long) 2);
        List<Evaluation> evaluations = evaluationService.selectByUser(member);
        System.out.println(evaluations);

    }
}
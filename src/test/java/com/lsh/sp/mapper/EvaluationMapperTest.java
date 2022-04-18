package com.lsh.sp.mapper;

import com.lsh.sp.pojo.vo.ScoreStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 12:45 2022/4/16
 **/
@SpringBootTest
class EvaluationMapperTest {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Test
    void scoreStatistics() {
        List<ScoreStatistics> scoreStatistics = evaluationMapper.scoreStatistics((long) 1);
        System.out.println(scoreStatistics);


    }
}
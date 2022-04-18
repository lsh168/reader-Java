package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.vo.ScoreStatistics;
import com.lsh.sp.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 13:52 2022/4/16
 **/
@RestController
@RequestMapping("evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @GetMapping("scoreStatistics")
    public ResponseResult scoreStatistics(Long bookId){
        Map map = evaluationService.selectScoreStatistics(bookId);

        return new ResponseResult(200,"success",map);
    }
}

package com.lsh.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.vo.ScoreStatistics;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:54 2022/1/12
 **/
@Repository
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    List<ScoreStatistics> scoreStatistics(Long bookId);
}

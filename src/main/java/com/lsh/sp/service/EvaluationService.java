package com.lsh.sp.service;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.pojo.vo.EvaluationBookMemberVo;
import com.lsh.sp.pojo.vo.ScoreStatistics;

import java.util.List;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 11:54 2022/1/13
 **/

public interface EvaluationService {
    List<Evaluation> selectByBookId(Long bookId);
    Map selectScoreStatistics(Long bookId);
//    通过用户查询评论
    List<Evaluation> selectByUser(Member member);
//    删除评论
    void deleteEvaluation(Long id);

//    后台短评管理
    List<EvaluationBookMemberVo> selectEvaluationState();
//    后台管理禁用短评
    ResponseResult updateEvaluation(Evaluation evaluation);

}

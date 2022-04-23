package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.mapper.BookMapper;
import com.lsh.sp.mapper.EvaluationMapper;
import com.lsh.sp.mapper.MemberMapper;
import com.lsh.sp.pojo.Book;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.pojo.vo.EvaluationBookMemberVo;
import com.lsh.sp.pojo.vo.ScoreStatistics;
import com.lsh.sp.service.EvaluationService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 11:55 2022/1/13
 **/
@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private MemberMapper memberMapper;
    /**
     * 按图书编号查询有效短评
     *
     * @param bookId 图书编号
     * @return 评论列表
     */
    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<Evaluation>();
        queryWrapper.eq("book_id",bookId);
        queryWrapper.eq("state","enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);
        //evaluation 加入两个不参与sql查询的新成员变量
        for (Evaluation ev:evaluationList){
            Member member = memberMapper.selectById(ev.getMemberId());
            ev.setBook(book);
            ev.setMember(member);
        }
        return evaluationList;
    }

    @Override
    public Map selectScoreStatistics(Long bookId) {
        List<ScoreStatistics> scoreStatistics = evaluationMapper.scoreStatistics(bookId);
        Map result=new HashMap();
//        五个分数段的统计
        List<List> scores=new ArrayList();
        while (scores.size()<5){
            scores.add(new LinkedList());
        }
        int sum=0;
        for (ScoreStatistics s : scoreStatistics) {
            sum=sum+s.getSum();//评论总条数
        }

        for (ScoreStatistics s : scoreStatistics) {
//            存评论的数量  一个分数占的百分比
            List item = new LinkedList<>();
            item.add(s.getSum());
            double percent=s.getSum()/(sum*1.0);
            double percentage = percent * 100;

            item.add(percentage);
            scores.set(s.getScore() - 1, item);//0-4，对应1-5，填上对应的人数
        }
        result.put("scoreStatistics",scores);
        result.put("count",sum);


        return result;
    }

    @Override
    public List<EvaluationBookMemberVo> selectEvaluationState() {
        return evaluationMapper.selectEvaluationState();
    }

    @Override
    public ResponseResult updateEvaluation(Evaluation evaluation) {
        if (evaluation.getState().equals("disable")) {
            evaluation.setDisableTime(new Date());
        }
        evaluationMapper.updateById(evaluation);
        Evaluation result = evaluationMapper.selectById(evaluation.getEvaluationId());
        return new ResponseResult(200,"success",result);
    }
}

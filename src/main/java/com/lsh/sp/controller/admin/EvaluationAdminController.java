package com.lsh.sp.controller.admin;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.vo.EvaluationBookMemberVo;
import com.lsh.sp.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 9:24 2022/4/21
 **/
@RestController
@RequestMapping("adminEvaluation")
public class EvaluationAdminController {
    @Autowired
    private EvaluationService evaluationService;

//    查询短评状态
    @PostMapping("selectState")
    public ResponseResult selectEvaluationState(){
        List<EvaluationBookMemberVo> evaluationBookMemberVos = evaluationService.selectEvaluationState();
        return new ResponseResult(200,"success",evaluationBookMemberVos);
    }

    @PostMapping("update")
    public ResponseResult updateState(Evaluation evaluation){
        return evaluationService.updateEvaluation(evaluation);

    }
}

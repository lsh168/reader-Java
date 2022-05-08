package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Label;
import com.lsh.sp.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:29 2022/5/8
 **/
@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @GetMapping("list")
    public ResponseResult list(){
        List<Label> list = labelService.list();
        return new ResponseResult(200,"success",list);
    }
}

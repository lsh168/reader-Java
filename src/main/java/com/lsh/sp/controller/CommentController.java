package com.lsh.sp.controller;

import com.lsh.sp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 12:46 2022/5/7
 **/
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

}

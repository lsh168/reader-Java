package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.pojo.Comment;
import com.lsh.sp.service.CommentService;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.HTTPUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private UserService userService;
    @PostMapping("insert")
    public ResponseResult insert(HttpServletRequest request,Comment comment){
        Long userId = HTTPUserId.getUserId(request);
//        ResponseResult responseResult = userService.userInfo(userId);
//        User user = (User) responseResult.getData();
        comment.setUserId(userId);
//        帖子的id
//        comment.setArticleId();


        boolean save = commentService.save(comment);
        if (save!=true)
            return new ResponseResult(201,"error");
        return new ResponseResult(200,"success",comment);

    }

}

package com.lsh.sp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.pojo.Comment;
import com.lsh.sp.pojo.Topic;
import com.lsh.sp.service.CommentService;
import com.lsh.sp.service.TopicService;
import com.lsh.sp.service.UserService;
import com.lsh.sp.utils.HTTPUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 19:17 2022/5/6
 **/
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
//    查询所有
    @GetMapping("list")
    public ResponseResult SelectTopic(){
//        List<Topic> list = topicService.list();
        QueryWrapper<Topic> qw=new QueryWrapper<>();
        qw.orderByDesc("createtime");
        List<Topic> list = topicService.list(qw);
//        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
//
//        for (Topic t : list) {
//            queryWrapper.eq("article_id",t.getId());
//            List<Comment> commentList = commentService.list(queryWrapper);
//
//        }
        for (Topic topic : list) {
            ResponseResult userInfo = userService.userInfo(topic.getUserid());
            topic.setUser((User) userInfo.getData());
        }
        return new ResponseResult(200,"success",list);
    }
//    分页查询
    @PostMapping("page")
    public ResponseResult page(Integer currentPage,Integer size){
        IPage<Topic> iPage=new Page<>(currentPage,size);
        IPage<Topic> page = topicService.page(iPage);
        return  new ResponseResult(200,"",page);
    }
//    新增
//    这里用户传入内容，和标签。
    @PostMapping("insert")
    public ResponseResult insert(HttpServletRequest request,Topic topic){
        Long userId = HTTPUserId.getUserId(request);
        topic.setCreatetime(new Date(System.currentTimeMillis()));
        topic.setReply(0L);
        topic.setThumbup(0L);
        topic.setVisits(0L);
        topic.setUserid(userId);
        ResponseResult responseResult = userService.userInfo(userId);
        User data = (User) responseResult.getData();
        topic.setUser(data);
        boolean save = topicService.save(topic);
        if (!save)
            return new ResponseResult(300,"插入失败！");
        return new ResponseResult(200,"success",topic);
    }
//    传入内容，传入id，
    @PostMapping("update")
    public ResponseResult update(Topic topic){
        topic.setUpdatetime(new Date());
        topicService.saveOrUpdate(topic);
        return new ResponseResult(200,"success",topic);
    }
    @DeleteMapping("delete")
    public ResponseResult delete(Topic topic){
        topicService.removeById(topic);
        return new ResponseResult(200,"");
    }
//    点赞
    @PostMapping("enjoy")
    public ResponseResult enjoy(Topic topic){
        topic.setThumbup(topic.getThumbup()+1);
        topicService.saveOrUpdate(topic);
        return new ResponseResult(200,"点赞成功！",topic);
    }
}

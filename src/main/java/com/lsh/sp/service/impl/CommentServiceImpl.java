package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.sp.mapper.CommentMapper;
import com.lsh.sp.pojo.Comment;
import com.lsh.sp.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 12:44 2022/5/7
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}

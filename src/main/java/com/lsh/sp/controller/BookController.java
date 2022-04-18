package com.lsh.sp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.domain.User;
import com.lsh.sp.mapper.UserMapper;
import com.lsh.sp.pojo.*;
import com.lsh.sp.service.BookService;
import com.lsh.sp.service.CategoryService;
import com.lsh.sp.service.EvaluationService;
import com.lsh.sp.service.MemberService;
import com.lsh.sp.utils.HTTPUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 15:55 2022/1/7
 **/
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("categoryList")
    public Map categoryAll(){
        Map result=new HashMap();
        List<Category> categoryList = categoryService.selectAll();
        result.put("categoryList",categoryList);
        return result;
    }
    /**
     * @Author luminous
     * @Description //TODO 查看图书详情
     * @Date 11:51 2022/1/13
     * @param bookId:
     * @return com.lsh.reader.pojo.Book
    **/
    @GetMapping("{bookId}")
    public Map showDetail(@PathVariable("bookId") Long bookId, HttpServletRequest request){
        Map map=new HashMap();
        Book book = bookService.selectById(bookId);
        List<Evaluation> evaluationList = evaluationService.selectByBookId(bookId);
        Long userId = HTTPUserId.getUserId(request);
        User user = userMapper.selectById(userId);

//        Member member=(Member)session.getAttribute("loginMember");

        if (user!=null){
            MemberReadState memberReadState = memberService.selectMemberReadState(user.getId(), bookId);
            map.put("memberReadState",memberReadState);
        }
        map.put("book",book);
        map.put("evaluationList",evaluationList);
        return map;
    }
    /**
     * @Author luminous
     * @Description //TODO 查询图书列表
     * @Date 20:52 2022/1/13
     * @param categoryId: 分类编号
 * @param order: 分类条件
 * @param page: 页数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.lsh.reader.pojo.Book>
    **/
    @GetMapping("selectBooks")
    public IPage<Book> selectBooks(Long categoryId,String order,Integer page){
        if (page==null)
            page=1;
        IPage<Book> pageObject = bookService.paging(categoryId, order, page, 10);
        return pageObject;
    }

    @GetMapping("SelectReadState")
    public ResponseResult selectReadState(Long bookId){

        return memberService.selectReadStateByMap(bookId);
    }

}
package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsh.sp.mapper.BookMapper;
import com.lsh.sp.pojo.Book;
import com.lsh.sp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> findAllBook() {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        List<Book> bookList = bookMapper.selectList(queryWrapper);
        return bookList;
    }
    /**
     * @Author luminous
     * @Description //TODO 图书的分页查询
     * @Date 10:53 2022/4/3
     * @param categoryId: 分类编号
     * @param order: 条件
     * @param page: 页号
     * @param rows: 每页记录数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.lsh.admin.entity.Book>
     **/
    @Override
    public IPage<Book> bookPage(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p=new Page<>(page,rows);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<Book>();
        if (categoryId!=null)
            queryWrapper.eq("category_id",categoryId);
        if (order!=null)
            if (order.equals("quantity"))
                queryWrapper.orderByDesc("evaluation_quantity");
            else if (order.equals("score"))
                queryWrapper.orderByDesc("evaluation_score");
        IPage<Book> pageObject=bookMapper.selectPage(p,queryWrapper);

        return pageObject;
    }

    @Override
    public Object scoreStatistics(Long bookId) {
//        bookMapper.selectById()
        return null;
    }

    /**
     * 分页查询图书
     *
     * @param categoryId 分类编号
     * @param order      排序方式
     * @param page       页号
     * @param rows       每页记录数
     * @return 分页对象
     */
    @Override
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p=new Page<>(page,rows);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        if (categoryId!=null&&categoryId!=-1){
            queryWrapper.eq("category_id",categoryId);
        }
        if (order!=null){
            if (order.equals("quantity"))
                queryWrapper.orderByDesc("evaluation_quantity");
            else if (order.equals("score"))
                queryWrapper.orderByDesc("evaluation_score");
        }
        IPage<Book> pageObject=bookMapper.selectPage(p,queryWrapper);
        return pageObject;
    }

    @Override
    public Book selectById(Long bookId) {
        Book book= bookMapper.selectById(bookId);
        return book;
    }

    /**
     * 更新图书评分/评价数量
     */
    @Override
    public void updateEvaluation() {

    }

    /**
     * 创建新的图书
     *
     * @param book
     */
    @Override
    public Book createBook(Book book) {
        return null;
    }

    /**
     * 更新图书
     *
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Override
    public Book updateBook(Book book) {
        return null;
    }

    /**
     * 删除图书及相关数据
     *
     * @param bookId 图书编号
     */
    @Override
    public void deleteBook(Long bookId) {

    }
}

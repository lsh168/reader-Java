package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.mapper.BookMapper;
import com.lsh.sp.mapper.EvaluationMapper;
import com.lsh.sp.mapper.MemberReadStateMapper;
import com.lsh.sp.pojo.Book;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.MemberReadState;
import com.lsh.sp.service.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private MemberReadStateMapper memberReadStateMapper;

    @Autowired
    private EvaluationMapper evaluationMapper;
    @Override
    public List<Book> findAllBook() {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("deleted",0);
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
        queryWrapper.eq("deleted",0);
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
    public List<Book> findHotBook() {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("evaluation_score");
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
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
        queryWrapper.eq("deleted",0);
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
        bookMapper.updateEvaluation();

    }

    /**
     * 创建新的图书
     *
     * @param book
     */
    @Override
    public ResponseResult createBook(Book book) {

            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            book.setDeleted(0);

            Document doc = Jsoup.parse(book.getDescription());
            Element img = doc.select("img").first();
            String cover = img.attr("src");
            book.setCover(cover);
            bookMapper.insert(book);


        return new ResponseResult(200,"success",book);
    }

    /**
     * 更新图书
     *
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Override
    public Book updateBook(Book book) {
        Book book1 = bookMapper.selectById(book.getBookId());
        book1.setAuthor(book.getAuthor());
        book1.setBookName(book.getBookName());
        book1.setCategoryId(book.getCategoryId());

        book1.setDescription(book.getDescription());
        book1.setSubTitle(book.getSubTitle());
        String cover = Jsoup.parse(book.getDescription()).select("img").first().attr("src");
        book1.setCover(book.getCover());
        bookMapper.updateById(book);
        return book;
    }

    /**
     * 删除图书及相关数据
     */
    @Override
    public void deleteBook(Book book) {
//        bookMapper.deleteById(bookId);
        book.setDeleted(1);
        bookMapper.updateById(book);
//        bookMapper.deleteById(book);
//        删除用户阅读状态
        QueryWrapper<MemberReadState> q1=new QueryWrapper<MemberReadState>();
        q1.eq("book_id",book.getBookId());
        memberReadStateMapper.delete(q1);
//        删除用户评论
        QueryWrapper<Evaluation> evaluationQueryWrapper=new QueryWrapper<>();
        evaluationQueryWrapper.eq("book_id",book.getBookId());
        evaluationMapper.delete(evaluationQueryWrapper);
    }

    @Override
    public List<Book> selectDeletedBook() {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("deleted",1);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    @Override
    public Book updateBookDeleted(Book book) {
//        0表示恢复删除的图书
        book.setDeleted(0);
        bookMapper.updateById(book);
        return book;
    }
}

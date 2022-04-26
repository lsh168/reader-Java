package com.lsh.sp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Book;

import java.util.List;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 15:57 2022/1/7
 **/
public interface BookService {
    List<Book> findAllBook();
    IPage<Book> bookPage(Long categoryId,String order,Integer page,Integer rows);
//    查询热门图书
    List<Book> findHotBook();
//  排行榜
    Map rankingList();
//    通过阅读状态查询图书
    List<Book> selectReadStateByUser(Integer readState,Long memberId);

    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Long categoryId , String order , Integer page, Integer rows);

    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    public Book selectById(Long bookId);

    /**
     * 更新图书评分/评价数量
     */
    void updateEvaluation();
    /**
     * 创建新的图书
     */
    ResponseResult createBook(Book book);

    /**
     * 更新图书
     * @param book 新图书数据
     * @return 更新后的数据
     */
    Book updateBook(Book book);

//    假删除
    void deleteBook(Book book);
//    回收站功能查询删除的数据
    List<Book> selectDeletedBook();
//    把删除的的图书进行恢复
    Book updateBookDeleted(Book book);
}

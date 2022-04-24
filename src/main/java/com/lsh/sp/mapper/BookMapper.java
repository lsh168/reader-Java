package com.lsh.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.sp.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:04 2022/1/7
 **/
@Repository
public interface BookMapper extends BaseMapper<Book> {
//    更新图书评分
    void updateEvaluation();
//   本月最热
    List<Long> monthBookId();
    List<Long> yearBookId();

}

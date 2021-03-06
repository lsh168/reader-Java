package com.lsh.sp.mapper;

import com.lsh.sp.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 10:47 2022/4/24
 **/
@SpringBootTest
class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void monthBookId() {
        List list = bookMapper.yearBookId();
        System.out.println(list);

    }

    @Test
    void userReadState() {
        List<Book> books = bookMapper.userReadState(1, (long) 2);
        System.out.println(books);
    }
}
package com.lsh.sp.service;

import com.lsh.sp.pojo.Category;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 11:42 2022/1/13
 **/
public interface CategoryService {
    /**
     * @Author luminous
     * @Description //查询所有图书分类
     * @Date 11:45 2022/1/13
     * @param :
     * @return java.util.List<com.lsh.reader.pojo.Category>
    **/
    List<Category> selectAll();
}

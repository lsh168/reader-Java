package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsh.sp.mapper.CategoryMapper;
import com.lsh.sp.pojo.Category;
import com.lsh.sp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 11:43 2022/1/13
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectList(new QueryWrapper<Category>());
    }
}

package com.lsh.sp.controller.admin;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Category;
import com.lsh.sp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:35 2022/4/20
 **/
@RestController
@RequestMapping("adminCategory")
public class CategoryAdminController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("add")
    public ResponseResult addCategory(Category category){
        categoryService.addCategory(category);
        return new ResponseResult(200,"success",category);

    }
}

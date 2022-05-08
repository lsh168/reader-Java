package com.lsh.sp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 11:03 2022/4/6
 **/
@RestController
public class TestController {

    @GetMapping("hello")
    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello(){
        return "hello!";
    }
}

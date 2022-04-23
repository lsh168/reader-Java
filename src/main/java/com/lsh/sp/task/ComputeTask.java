package com.lsh.sp.task;

import com.lsh.sp.service.BookService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 21:35 2022/4/19
 **/
@Component
@EnableScheduling
public class ComputeTask {
    @Resource
    private BookService bookService;
    //任务调度
    @Scheduled(cron = "0 * * * * ?")
    public void updateEvaluation(){
        bookService.updateEvaluation();
        System.out.println("已更新所有图书评分");
    }
}

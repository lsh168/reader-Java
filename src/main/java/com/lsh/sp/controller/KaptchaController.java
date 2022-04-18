package com.lsh.sp.controller;

import com.google.code.kaptcha.Producer;
import com.lsh.sp.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 19:38 2022/1/12
 **/
@RestController
public class KaptchaController {
    @Resource
    private Producer kaptchaProducer;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/verify_code")
    public void createVerifyCode(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //响应立即过期
        response.setDateHeader("Expires",0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control" , "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control" , "post-check=0,pre-check=0");
        response.setHeader("Pragma" , "no-cache");
        response.setContentType("image/png");
        //生成验证码字符文本
        String verifyCode = kaptchaProducer.createText();
//        把验证码存入缓存
        redisCache.setCacheObject("kaptchaVerifyCode",verifyCode);
        String kaptchaVerifyCode = redisCache.getCacheObject("kaptchaVerifyCode");
        System.out.println(kaptchaVerifyCode);
//        request.getSession().setAttribute("kaptchaVerifyCode",verifyCode);
//        System.out.println(request.getSession().getAttribute("kaptchaVerifyCode"));
        BufferedImage image = kaptchaProducer.createImage(verifyCode);//创建验证码图片
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);//输出图片流
        out.flush();
        out.close();
    }

}

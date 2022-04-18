package com.lsh.sp.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:12 2022/1/12
 **/
@Configuration
public class KapchaConfig {
    @Bean(name="kaptchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        //不生成边框
        properties.setProperty("kaptcha.border", "no");
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        验证码图片字体颜色为蓝色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
//        验证码图片宽度为120像素
        properties.setProperty("kaptcha.image.width", "120");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.session.key", "code");
//        验证码包含4个字符
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

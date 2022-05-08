package com.lsh.sp.utils;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author luminous
 * @Description //获取请求头中的userId
 * @Date 21:37 2022/4/15
 **/

public class HTTPUserId {
    public static Long getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println(token);
        //解析token
        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        return userId;
    }

}

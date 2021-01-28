package com.nowcoder.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个工具功能是 从request中获取所有携带的cookie 并且来判断cookie中的name字段的值是什么
 */
public class CookieUtil {

    public static String getValue(HttpServletRequest request,String name){
        if(request==null || name==null){
            throw new IllegalArgumentException("参数为空");
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c:cookies){
                if(c.getName().equals(name)){
                    return c.getValue();
                }
            }
        }

        return null;

    }
}

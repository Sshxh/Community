package com.nowcoder.community.controller.interceptor;


import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CookieUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取凭证
        String ticket = CookieUtil.getValue(request,"ticket");

        if(ticket!=null){
            //查询凭证（是否有效，包括了status=0 并且expiered时间有效）
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            if(loginTicket!=null && loginTicket.getStatus()==0 && loginTicket.getExpired().after(new Date())){

                //根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUserId());
                //ThreadLcoal 在本次请求当中持有用户(是一个多线程的情况）
                hostHolder.setUser(user);
            }
        }
        return true;
    }

    /**
     * 这个方法是在调用模版之前被调用的，这样子我们就可以根据目前登陆的用户的ticket找到userid
     * 再通过userid找到我们的User类型user的信息
     * 得到了user信息之后我们要把信息添加到Model传给我们的template （并且是在调用template之前需要传入）
     * 所以这里拦截器我们复用了postHandle方法
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user != null && modelAndView!=null){
            modelAndView.addObject("loginUser",user);
        }
    }

    /**
     *当模版都执行完以后 最后我们需要对ThreadLocal线程中添加的数据进行一个清除
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}

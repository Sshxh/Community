package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "/site/login";
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){

        //UserService已经全部封装了我们的注册功能 并且把数据都传到了Map过来
        Map<String, Object> map = userService.register(user);
        //Post完之后我们前端会提交表单 传来一个User数据
        //我们利用这个User数据来传入到我们的register来进行判断逻辑（也就是这个user是否被正确的注册进我们UserMapper）

        //证明了map没有携带提示信息，是一个成功注册的标志
        if(map==null || map.isEmpty()){
            model.addAttribute("msg","注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活");

            //我们需要让中间的页面跳转到这个主页中 所以携带了一个target数据
            model.addAttribute("target","/index");

            //跳转到一个注册成功 等待激活的页面
            return "/site/operate-result";
        }
        //不成功注册 map中带有提示的msg
        else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            //返回重新注册的页面 这个时候我们的map已经携带了很多msg 我们可以根据msg来判断是说明原因
            return "/site/register";
        }

    }


    //当我们点开我们的激活链接的时候就会进行判断 根据url的userId和code来判断是否能够正确的被激活！
    @GetMapping("/activation/{userId}/{code}")
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.activation(userId, code);
        switch (result) {
            case ACTIVATION_SUCCESS:
                model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
                model.addAttribute("target", "/login");
                break;
            case ACTIVATION_REPEAT:
                model.addAttribute("msg", "重复操作,您的账号已经激活过了!");
                model.addAttribute("target", "/index");
                break;
            default:
                model.addAttribute("msg", "激活失败,请检查您的激活码是否正确!");
                model.addAttribute("target", "/index");
                break;
        }
        return "/site/operate-result";
    }
}

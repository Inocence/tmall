package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.service.UserService;
import com.baiyu.tmall.util.Result;
import com.baiyu.tmall.util.ResultEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(User user, Model model) {
        System.out.println(user);
        Subject shiro = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            shiro.login(token);
            return "redirect:/";
        } catch (UnknownAccountException e) {
            model.addAttribute("message", "账号不存在！");
        } catch (DisabledAccountException e) {
            model.addAttribute("message", "账号未启用！");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码错误！");
        } catch (Throwable e) {
            e.printStackTrace();
            model.addAttribute("message", "未知错误！");
        }
        return "login/login";
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "login/register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        Result<User> result = userService.create(user);
        if(result.getCode().equals("0")) {
            userService.sendEmail(result.getData().getEmail(), result.getData().getVerify());
        }
        model.addAttribute("message", result.getMsg());
        return "login/register";
    }

    @GetMapping("/verify")
    public String verify(@Param("code") String code, Model model) {
        boolean res = userService.verify(code);
        if(res) {
            model.addAttribute("message", "验证成功");
        } else {
            model.addAttribute("message", "验证失败");
        }
        return "login/verify";
    }
}

package com.springmvc.controller;

import com.springmvc.entity.Message;
import com.springmvc.entity.User;
import com.springmvc.service.MessageService;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@Controller
public class LoginCtroller {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public Message login(HttpServletRequest req) {
//        messageService = new MessageService();
        String id = req.getParameter("id");
        int i = Integer.valueOf(id);
        return messageService.selectByPrimaryKey(i);
    }

    @ResponseBody
    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public ModelAndView toRegister() {
        ModelAndView view = new ModelAndView();
        view.setViewName("registerPage");
        return view;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json;charset=GBK")
    public String register(HttpServletRequest request) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if(!password.equals(password1)) {
            return "密码不一致！";
        }else {
            User user = new User();
            user.setUsername(name);
            user.setPassword(password);
            int result = userService.insertSelective(user);
            if(result == 1) {
                return "注册成功！";
            }else {
                return "注册失败！";
            }
        }
    }
}

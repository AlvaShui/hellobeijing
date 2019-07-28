package com.springmvc.controller;

import com.springmvc.entity.Message;
import com.springmvc.entity.User;
import com.springmvc.service.MessageService;
import com.springmvc.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ResponseBody
@Controller
public class LoginCtroller {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=GBK")
    public String login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User result = userService.loginSql(user);
        if (result!=null) {
            return "登录成功！";
        }else {
            return "用户名密码不正确！";
        }
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

    @RequestMapping(value="/userList",produces = "application/json;charset=GBK")
    public String userList() {
        List<User> userList = userService.selectUserList();
        JSONArray jsonArray = new JSONArray();
        for (User u:userList) {
            JSONObject jo = new JSONObject();
            jo.put("id",u.getId());
            jo.put("username",u.getUsername());
            jsonArray.add(jo);
        }
        return jsonArray.toString();
    }
}

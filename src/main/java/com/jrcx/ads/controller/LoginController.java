package com.jrcx.ads.controller;

import com.jrcx.ads.controller.BaseController;
import com.jrcx.ads.domain.User;
import com.jrcx.ads.domain.dao.UserRepository;
import com.jrcx.ads.res.BaseRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


import static com.jrcx.ads.interceptor.LoginCheckInterceptor.SESSION_KEY_USER;

/**
 * @author Administrator.
 * @date 2017/11/25
 */
@Controller
@RequestMapping(value = "/api/user")
public class LoginController extends BaseController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes login(@RequestParam String name, @RequestParam String password, HttpServletRequest request) {
        boolean flag = false;

        BaseRes res = new BaseRes();

        User user = userRepository.findByName(name);

        if (user == null) {
            res.setSuccess(false);
            res.setErrorMsg("用户名不存在");
        } else if (!user.getPassword().equals(password)) {
            res.setSuccess(false);
            res.setErrorMsg("密码错误");
        } else {
            request.getSession().setAttribute(SESSION_KEY_USER, user);
        }

        return res;
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public BaseRes register(@RequestParam String name, @RequestParam String password){
        BaseRes res = new BaseRes();

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUpdateDate(new Date());
        user.setUpdateDate(new Date());

        if(userRepository.findByName(name) != null){
            res.setSuccess(false);
            res.setErrorMsg("用户名已存在");
        }else {
            userRepository.save(user);
        }

        return res;
    }

}

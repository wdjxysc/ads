package com.jrcx.ads.service;

import com.jrcx.ads.domain.User;
import com.jrcx.ads.domain.dao.UserRepository;
import com.jrcx.ads.res.BaseRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Administrator.
 * @date 2017/11/25
 */
@Controller
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes login(HttpServletRequest request){
        BaseRes res = new BaseRes();

        Map map = request.getParameterMap();

        String name = map.get("name").toString();

        User user = userRepository.findByName(name);

        if(user == null) {
            res.setSuccess(false);
            res.setErrorMsg("用户不存在");
        }else {
            if(!user.getPassword().equals(map.get("password").toString())){
                res.setSuccess(false);
                res.setErrorMsg("密码错误");
            }
        }

        return res;
    }

}

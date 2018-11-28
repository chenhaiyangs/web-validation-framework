package com.web.validation.boot.test.controller;

import com.web.validation.boot.test.vo.User;
import com.web.validation.spring.annotation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(value = "/verfity",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User sayHello(@RequestBody @Valid User user){
        return user;
    }
}

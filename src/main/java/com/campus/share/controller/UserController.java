package com.campus.share.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login/{username}")
    public JSONObject hello(@PathVariable String username) {
        JSONObject object = new JSONObject();
        object.put("data", "hello:" + username);
        return object;
    }

}

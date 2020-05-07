package com.joe.gmail.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.joe.gmail.bean.UmsMember;
import com.joe.gmail.bean.UmsMemberReceiveAddress;
import com.joe.gmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){

        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = userService.getReceiveAddressByMemberId(memberId);

        return umsMemberReceiveAddressList;
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){

        List<UmsMember> umsMember = userService.getAllUser();

        return umsMember;
    }


    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }
}

package com.joe.gmail.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.joe.gmail.bean.UmsMember;
import com.joe.gmail.bean.UmsMemberReceiveAddress;
import com.joe.gmail.service.UserService;
import com.joe.gmail.user.mapper.UmsMemberReceiveAddressMepper;
import com.joe.gmail.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UmsMemberReceiveAddressMepper umsMemberReceiveAddressMepper;

    @Override
    public List<UmsMember> getAllUser() {

        List<UmsMember> umsMemberList = userMapper.selectAll();//userMapper.selectAllUser();
        return umsMemberList;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {

        //封装到参数对象
        //UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        //umsMemberReceiveAddress.setMemberId(memberId);
        //List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMepper.select(umsMemberReceiveAddress);

        //哪个字段不为空，就把这个当成条件去查
        //List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMepper.selectByExample(umsMemberReceiveAddress);

        Example e = new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId",memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMepper.selectByExample(e);


        return umsMemberReceiveAddressList;
    }
}

package com.joe.gmail.service;

import com.joe.gmail.bean.UmsMember;
import com.joe.gmail.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}

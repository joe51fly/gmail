package com.joe.gmail.service;

import com.joe.gmail.bean.PmsBaseAttrInfo;
import com.joe.gmail.bean.PmsBaseAttrValue;

import java.util.List;

public interface AttrService {


    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
}

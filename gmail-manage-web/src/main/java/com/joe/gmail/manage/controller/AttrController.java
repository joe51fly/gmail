package com.joe.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.joe.gmail.bean.PmsBaseAttrInfo;
import com.joe.gmail.bean.PmsBaseAttrValue;
import com.joe.gmail.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;


    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){

        String success = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    };

    /**
     *
     * @param attrId
     * @return pmsBaseAttrValues商品属性值List集合
     */
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.getAttrValueList(attrId);
        return pmsBaseAttrValues;
    };

    /**
     * @param catalog3Id
     * @return pmsBaseAttrInfos商品属性信息List集合
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    };
}

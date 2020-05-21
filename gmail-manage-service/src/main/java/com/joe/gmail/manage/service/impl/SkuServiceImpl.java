package com.joe.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.joe.gmail.bean.PmsSkuAttrValue;
import com.joe.gmail.bean.PmsSkuImage;
import com.joe.gmail.bean.PmsSkuInfo;
import com.joe.gmail.bean.PmsSkuSaleAttrValue;
import com.joe.gmail.manage.mapper.PmsSkuAttrValueMapper;
import com.joe.gmail.manage.mapper.PmsSkuImageMapper;
import com.joe.gmail.manage.mapper.PmsSkuInfoMapper;
import com.joe.gmail.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.joe.gmail.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        //插入skuinfo
        int i = pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuInfoId = pmsSkuInfo.getId();

        //插入skuAttrValue
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuInfoId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //插入skuSaleAttrValue
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuInfoId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        //插入skuImage
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuInfoId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

    }
}

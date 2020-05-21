package com.joe.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.joe.gmail.bean.*;
import com.joe.gmail.manage.mapper.PmsBaseSaleAttrMapper;
import com.joe.gmail.manage.mapper.PmsProductImageMapper;
import com.joe.gmail.manage.mapper.PmsProductInfoMapper;
import com.joe.gmail.manage.mapper.PmsProductSaleAttrMapper;
import com.joe.gmail.manage.mapper.PmsProductSaleAttrValueMapper;
import com.joe.gmail.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {

        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.select(pmsProductInfo);

        return pmsProductInfos;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        List<PmsBaseSaleAttr> pmsProductSaleAttrs = pmsBaseSaleAttrMapper.selectAll();
        return pmsProductSaleAttrs;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {

        String id = pmsProductInfo.getId();
        if (StringUtils.isBlank(id)){
            //id为空，插入Info
            pmsProductInfoMapper.insertSelective(pmsProductInfo);
            //从info对象取出image，productId一对多个image对象，并根据id插入
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage pmsProductImage : spuImageList){
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageMapper.insertSelective(pmsProductImage);
            }

            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList){
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);

                List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList){
                    pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
                    pmsProductSaleAttrValue.setProductId(pmsProductSaleAttr.getProductId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
                }
            }
        } else {
            //id不为空，修改info
            Example example = new Example(PmsProductInfo.class);
            example.createCriteria().andEqualTo("id",id);
            pmsProductInfoMapper.updateByExampleSelective(pmsProductInfo,example);
            //根据productId删除saleAttr的记录
            PmsProductSaleAttr pmsProductSaleAttrDel = new PmsProductSaleAttr();
            pmsProductSaleAttrDel.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.delete(pmsProductSaleAttrDel);
            //根据productId删除saleAttrValue的记录
            PmsProductSaleAttrValue pmsProductSaleAttrValueDel = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValueDel.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrValueMapper.delete(pmsProductSaleAttrValueDel);

            //删除完成后 再插入新的数据
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList){
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);

                List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList){
                    pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
                    pmsProductSaleAttrValue.setProductId(pmsProductSaleAttr.getProductId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
                }
            }
        }
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr pmsProductSaleAttr1 : pmsProductSaleAttrs){
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr1.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            pmsProductSaleAttr1.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }

        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.select(pmsProductImage);
        return pmsProductImages;
    }
}

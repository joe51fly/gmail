package com.joe.gmail.service;

import com.joe.gmail.bean.PmsBaseSaleAttr;
import com.joe.gmail.bean.PmsProductImage;
import com.joe.gmail.bean.PmsProductInfo;
import com.joe.gmail.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}

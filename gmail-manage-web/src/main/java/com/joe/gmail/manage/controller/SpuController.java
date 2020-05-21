package com.joe.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.joe.gmail.bean.PmsBaseSaleAttr;
import com.joe.gmail.bean.PmsProductImage;
import com.joe.gmail.bean.PmsProductInfo;
import com.joe.gmail.bean.PmsProductSaleAttr;
import com.joe.gmail.manage.util.PmsUploadUtil;
import com.joe.gmail.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){

        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);

        return pmsProductImages;
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);

        return pmsProductSaleAttrs;
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){

        //将图片或者视频上传到分布式文件储存系统
        //将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.upLoadImage(multipartFile);
        //System.out.println("imgUrl--------->"+imgUrl);
        return imgUrl;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){

        String saveSpuInfo = spuService.saveSpuInfo(pmsProductInfo);

        return saveSpuInfo;
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){

        List<PmsProductInfo> pmsProductInfos =  spuService.spuList(catalog3Id);

        return pmsProductInfos;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){

        List<PmsBaseSaleAttr> pmsProductInfos =  spuService.baseSaleAttrList();

        return pmsProductInfos;
    }
}

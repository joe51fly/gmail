package com.joe.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.joe.gmail.bean.PmsBaseCatalog1;
import com.joe.gmail.bean.PmsBaseCatalog2;
import com.joe.gmail.bean.PmsBaseCatalog3;
import com.joe.gmail.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){

        List<PmsBaseCatalog1> catalogs = catalogService.getCatalog1();
        return catalogs;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){

        List<PmsBaseCatalog2> catalogs = catalogService.getCatalog2(catalog1Id);
        return catalogs;
    }


    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){

        List<PmsBaseCatalog3> catalogs = catalogService.getCatalog3(catalog2Id);
        return catalogs;
    }
}

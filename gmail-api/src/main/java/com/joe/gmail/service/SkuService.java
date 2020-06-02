package com.joe.gmail.service;

import com.joe.gmail.bean.PmsSkuInfo;
import org.springframework.transaction.annotation.Transactional;

public interface SkuService {
    @Transactional
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);
}

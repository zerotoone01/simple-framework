package com.huangxi.service.combine.impl;

import com.huangxi.entity.dto.MainPageInfoDTO;
import com.huangxi.entity.dto.Result;
import com.huangxi.service.combine.HeadLineShopCategoryCombineService;
import org.simpleframework.core.annotation.Service;

/**
 * 测试多个接口实现，在获取bean实例时候的异常
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl2 implements HeadLineShopCategoryCombineService {
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        return null;
    }
}

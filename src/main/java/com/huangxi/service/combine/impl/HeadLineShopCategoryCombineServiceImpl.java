package com.huangxi.service.combine.impl;

import com.huangxi.entity.bo.HeadLine;
import com.huangxi.entity.bo.ShopCategory;
import com.huangxi.entity.dto.MainPageInfoDTO;
import com.huangxi.entity.dto.Result;
import com.huangxi.service.combine.HeadLineShopCategoryCombineService;
import com.huangxi.service.solo.HeadLineService;
import com.huangxi.service.solo.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @description TODO
 * @date 2020-07-21
 */
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    public static final Logger LOGGER =LoggerFactory.getLogger(HeadLineShopCategoryCombineServiceImpl.class);
    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1.获取头条列表 2.获取店铺类别列表 3.合并两者并返回
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        Result<List<HeadLine>> queryHeadLine = headLineService.queryHeadLine(headLine, 1, 100);

        ShopCategory shopCategory = new ShopCategory();
        Result<List<ShopCategory>> queryShopCategory = shopCategoryService.queryShopCategory(shopCategory, 1, 100);
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(queryHeadLine,queryShopCategory);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> queryHeadLine, Result<List<ShopCategory>> queryShopCategory) {
        return null;
    }
}

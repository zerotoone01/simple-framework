package com.huangxi.entity.dto;

import com.huangxi.entity.bo.HeadLine;
import com.huangxi.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @description TODO
 * @date 2020-07-21
 */
@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}

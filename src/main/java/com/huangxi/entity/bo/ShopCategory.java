package com.huangxi.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShopCategory {
    //主键ID
    private Long shopCategoryId;
    //类别名
    private String shopCategoryName;
    //类别描述
    private String shopCategoryDesc;
    //类别图片地址针对一级类别
    private String shopCategoryImg;
    //权重,越大越排前显示
    private Integer priority;
    //创建时间
    private Date createTime;
    //最近的更新时间
    private Date lastEditTime;
    //父类别
    private ShopCategory parent;
}

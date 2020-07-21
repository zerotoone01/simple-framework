package com.huangxi.controller.frontend;

import com.huangxi.entity.dto.MainPageInfoDTO;
import com.huangxi.entity.dto.Result;
import com.huangxi.service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageController {
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}

package com.huangxi.controller.superadmin;

import com.huangxi.entity.bo.HeadLine;
import com.huangxi.entity.dto.Result;
import com.huangxi.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 操作头条数据
 */
@Controller
public class HeadLineOperationController {
    @Autowired
    private HeadLineService headLineService;
    //TODO 参数校验以及请求参数转换
    public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp){
        return headLineService.addHeadLine(new HeadLine());
    }
    public Result<Boolean> removeHeadLine(HttpServletRequest req, HttpServletResponse resp){
        return headLineService.removeHeadLine(new HeadLine());
    }
    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse resp){
        return headLineService.modifyHeadLine(new HeadLine());
    }
    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse resp){
        return headLineService.queryHeadLineById(1);
    }
    public Result<List<HeadLine>> queryHeadLine(HttpServletRequest req, HttpServletResponse resp){
        return headLineService.queryHeadLine(null,1,100);
    }
}

package com.huangxi.controller;

import com.huangxi.consts.ServletPathConst;
import com.huangxi.controller.frontend.MainPageController;
import com.huangxi.controller.superadmin.HeadLineOperationController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description TODO
 * @date 2020-07-21
 */
@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据请求路径和方法，做不同的处理

        String servletPath = req.getServletPath();
        String reqMethod = req.getMethod();
        LOGGER.debug("servletPath=[{}], reqMethod=[{}]", servletPath,reqMethod);
        LOGGER.info("servletPath=[{}], reqMethod=[{}]", servletPath,reqMethod);
        if(req.getServletPath()== ServletPathConst.SERVLET_PATH_MAIN_PAGE_INFO
        && req.getMethod().toUpperCase()=="GET"){
            new MainPageController().getMainPageInfo(req,resp);
        }else if(req.getServletPath()==ServletPathConst.SERVLET_PATH_SUPER_ADMIN_ADD_HEAD
        && req.getMethod().toUpperCase()=="POST"){
            new HeadLineOperationController().addHeadLine(req,resp);
        }else {
            //TODO
            LOGGER.warn("no such request in service!!! method=[{}], path=[{}]",req.getMethod(),req.getServletPath());
        }
    }
}

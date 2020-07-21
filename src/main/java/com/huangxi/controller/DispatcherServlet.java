package com.huangxi.controller;

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

    }
}

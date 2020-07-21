package com.huangxi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  https://juejin.im/post/5e6e077e6fb9a07ca80ac376
 * @description TODO
 * @date 2020-07-20
 */
@WebServlet("/hello")
//@WebServlet("/*")  会导致死循环，req.getRequestDispatcher转发内容涉及到路径，就又会被拦截到里面来
public class HelloServlet extends HttpServlet {

    public static final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);
    @Override
    public void init() throws ServletException {
        System.out.println("servlet init start");
        LOGGER.info("servlet init start");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method real entrance");
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name="my simple framework";
        req.setAttribute("name",name);
        System.out.println("hello -- " +name);
        //内容转发到jsp页面
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
//        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(" Destroy....");
//        super.destroy();
    }
}

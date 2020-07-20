package com.huangxi;

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
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name="my simple framework";
        req.setAttribute("name",name);
        System.out.println("hello -- " +name);
        //内容转发到jsp页面
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
//        super.doGet(req, resp);
    }
}

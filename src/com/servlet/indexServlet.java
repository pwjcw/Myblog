package com.servlet;

import com.bean.UserBean;
import com.servlet.base.ViewBaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "indexServlet", value = "/index")
public class indexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            UserBean userBean = (UserBean) session.getAttribute("user");
            System.out.println(userBean);
            request.setAttribute("user","1");
            if (userBean.getUser_type() == 1) {
                request.setAttribute("admin", "1");
            } else {
                request.setAttribute("admin", "0");
            }
        } else {
            request.setAttribute("user", "0");
        }
        processTemplate("msg/msg", request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

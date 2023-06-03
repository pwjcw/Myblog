package com.servlet;

import com.bean.UserBean;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.servlet.base.ModelBaseServlet;
import com.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "User", value = "/user")
public class User extends ModelBaseServlet {
    private UserService userService=new UserServiceImpl();
    /**
     * 调转到登入界面
     * @param request
     * @param response
     * @throws IOException
     */
    public  void tologin(HttpServletRequest request,HttpServletResponse response) throws IOException {

            processTemplate("user/login",request,response);
    }

    /**
     * 调转到注册界面
     * @param request
     * @param response
     * @throws IOException
     */
    public  void toregister(HttpServletRequest request,HttpServletResponse response) throws IOException {
        processTemplate("user/register",request,response);
    }

    /**
     * 调转到信息页面
     * @param request
     * @param response
     * @throws IOException
     */
    public void tomsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
        processTemplate("msg/msg",request,response);
    }

    /**
     * 处理注册的功能
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request,HttpServletResponse response){
        //接收用户传输过来的数据，进行封装到bean对象中
        UserBean user = (UserBean) JsonUtils.parseJsonToBean(request, UserBean.class);
        System.out.println(user);
        Map<String,String> map=new HashMap<>();
        try {
            userService.addUser(user);   //没用异常则表示注册成功，调转到登入界面
            map.put("msg","1");
            JsonUtils.writeResult(response,map);
        } catch (Exception e) {
            map.put("msg",e.getMessage());  //将错误信息返回给前端
            JsonUtils.writeResult(response,map);
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理登入
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request,HttpServletResponse response){
        //接收用户传输过来的数据，进行封装到bean对象中
        UserBean user = (UserBean) JsonUtils.parseJsonToBean(request, UserBean.class);
        //如果没有抛出异常就说明，登入业务正常
        Map<String,String> lmap=new HashMap<>();
        try {
            UserBean login = userService.login(user);
            HttpSession session = request.getSession(true);   //登入成功，创建session
            session.setAttribute("user", login);
            lmap.put("msg","1");
            JsonUtils.writeResult(response,lmap);   //将lmap传递给前端,前端通过1(成功),0失败,进行相应的操作
        } catch (Exception e) {
            lmap.put("msg","0");
            JsonUtils.writeResult(response,lmap);
            throw new RuntimeException(e);
        }
    }

    /**
     * 退出登入的方法
     * @param request
     * @param response
     * @throws IOException
     */
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        session.invalidate(); //session失效
        processTemplate("user/login",request,response);
    }

    /**
     * admin界面的一些操作
     * @param request
     * @param response
     * @throws IOException
     */
    public void toadmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session!=null && session.getAttribute("user")!=null){
            UserBean userBean = (UserBean) session.getAttribute("user");
            if (userBean.getUser_type()==1){
                response.getWriter().write("<script>window.location.href='msg?method=showAllMsg';</script>");
            }else {
                response.getWriter().write("<script>alert('hacker Don't Want!!!')</script>");
                response.getWriter().write("<script>window.location.href='user?method=tomsg';</script>");
            }
        }else {
            response.getWriter().write("<script>alert('请先登入')</script>");
            response.getWriter().write("<script>window.location.href='user?method=tologin';</script>");
        }
    }
}

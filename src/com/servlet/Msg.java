package com.servlet;

import com.alibaba.fastjson.JSONObject;
import com.bean.MsgBean;
import com.bean.UserBean;
import com.google.gson.JsonObject;
import com.service.MsgService;
import com.service.impl.MsgServiceImpl;
import com.servlet.base.ModelBaseServlet;
import com.utils.JsonUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Msg", value = "/msg")
public class Msg extends ModelBaseServlet {
    private MsgService msgService=new MsgServiceImpl();

    /**
     * 调转到写文章的界面
     * @param request
     * @param response
     * @throws IOException
     */
    public void toWritemsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        System.out.println(session);
        if (session.getAttribute("user")==null){
            response.getWriter().write("<script>alert('请重新登入')</script>");
            response.getWriter().write("<script>window.location.href='user?method=tologin';</script>");
        }else {
            processTemplate("msg/addMsg",request,response);
        }

    }

    /**
     * 传递文章数据，处理异步加载
     * @param request
     * @param response
     */
    public void showMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = request.getReader();   //接收前端传输的内容
        StringBuilder stringBuilder = new StringBuilder();   //存储请求体
        String body = "";
        while ((body = bufferedReader.readLine()) != null) {
            stringBuilder.append(body);
        }
        System.out.println(stringBuilder);
        JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());     //将字符串转换为json对象
        Integer index=jsonObject.getInteger("index");    //获取json对象中的index值
        List<MsgBean> msgBeans = msgService.showMsg(index);    //调用业务层方法，进程查询数据
        JsonUtils.writeResult(response,msgBeans);
    }

    /**
     * 向admin界面展示所有的文章信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void showAllMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<MsgBean> msgBeans = msgService.showAllMsg();
        request.setAttribute("msg",msgBeans);   //往请求域中存入所有的文章信息
        processTemplate("user/admin/admin_msg",request,response);
    }

    /**
     * 添加文章
     * @param request
     * @param response
     */
    public void addMsg(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        MsgBean msgBean = new MsgBean();    //新建一个msgbean对象，用于存储文章信息
        Map<String,String []> param=request.getParameterMap();    //获取页面传递的参数内容
        BeanUtils.populate(msgBean,param);  //将参数信息封装到msgbean方法中
        HttpSession session = request.getSession(false);   //获取请求中的session
        UserBean user = (UserBean) session.getAttribute("user");   //从session获取user对象
        msgBean.setMsg_username(user.getUser_name());  //将session中读取的user对象username封装进msgbean对象
        System.out.println(msgBean);
        try {
            msgService.addMsg(msgBean);     //调用业务层方法存储数据
            response.getWriter().write("<script>alert('文章发布成功')</script>");
            response.getWriter().write("<script>window.location.href='user?method=tomsg';</script>");
        } catch (Exception e) {
            response.getWriter().write("<script>alert('文章发布失败')</script>");
            response.getWriter().write("<script>window.location.href='msg?method=toWritemsg';</script>");
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文章
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer msgId = Integer.valueOf(request.getParameter("msg_id"));
        msgService.deleteMsg(msgId);
        response.getWriter().write("<script>window.location.href='msg?method=showAllMsg';</script>");
    }

    /**
     * 调转到修改文章的界面
     * @param request
     * @param response
     */
    public void toupdateMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer msgId = Integer.valueOf(request.getParameter("msg_id"));   //获取传递的参数msg_id
        MsgBean msg = msgService.findMsg(msgId);    //通过msg_id查询整个msg的内容
//        System.out.println(msg);
        request.setAttribute("msg",msg);    //将msg传递到请求域中,thymeleaf接收并渲染
        processTemplate("user/admin/update_msg",request,response);
    }
    public void updateMsg(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
            Map<String,String[]> param=request.getParameterMap();    //接收用户提交的信息
            MsgBean msgBean=new MsgBean();
            BeanUtils.populate(msgBean,param);    //封装到bean方法中
            System.out.println(msgBean);
            msgService.updateMsg(msgBean);
            response.getWriter().write("<script>window.location.href='index';</script>");
    }

    /**
     * 调转到查看文章内容的页面
     * @param request
     * @param response
     * @throws IOException
     */
    public void toreadMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer msgId = Integer.valueOf(request.getParameter("msg_id"));   //获取请求参数中的的msg_id
        MsgBean msg = msgService.findMsg(msgId);   //通过msg_id获取整个msg信息
        request.setAttribute("msg",msg);
        processTemplate("msg/read_msg",request,response);
    }
}

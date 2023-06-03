package com.service.impl;

import com.bean.UserBean;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;
import com.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    /**
     *  处理注册的业务
     * @param userBean
     */
    public void addUser(UserBean userBean){
        System.out.println(userBean.getUser_name());
        //先判断用户名是否已经存在
        UserBean user = userDao.findUser(userBean.getUser_name());
        if (user==null){    //如何查询结果为空，则说明用户名不存在，可以进行注册
            String encodepwd= MD5Util.encode(userBean.getUser_pwd());   //将密码进行md5加密
            userBean.setUser_pwd(encodepwd);
            UserBean userBean1 = userDao.showFirstUser();
            if (userBean1==null){     //查看第一个用户是否存在，如果不存在，第一个用户就是管理员
                userBean.setUser_type(1);   //user_type表示用户类型，默认为0，非管理员
            }else {
                userBean.setUser_type(0);
            }

            userBean.setUser_state(1);   //user_state表示用户状态，默认为1，表示未封禁
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
            String time = sdf.format(new Date());   //获取当前时间
            userBean.setRegister_time(time);
            userDao.addUser(userBean);   //调用持久化层进行数据库操作
        }else throw new RuntimeException("用户名已存在");   //如果用户名存在则抛出异常
    }

    /**
     * 处理登入的业务，核心逻辑是根据用户登入输入的用户名，从数据库中查找用户数据，进行密码比对
     * @param userBean
     * @return
     */
    @Override
    public UserBean login(UserBean userBean){
        UserBean user = userDao.findUser(userBean.getUser_name());
        String encodepwd=MD5Util.encode(userBean.getUser_pwd());  //将用户发送的密码进行md5加密
        if (encodepwd.equals(user.getUser_pwd())){
            return user;
        }else {
            throw  new RuntimeException("用户名或密码错误");
        }
    }
}

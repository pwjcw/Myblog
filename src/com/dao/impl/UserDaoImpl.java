package com.dao.impl;

import com.bean.UserBean;
import com.dao.BaseDao;
import com.dao.UserDao;

public class UserDaoImpl extends BaseDao<UserBean> implements UserDao  {
    /**
     * 注册用户
     * @param userBean
     */
    @Override
    public void addUser(UserBean userBean){
        String sql="insert into user (user_name,user_pwd,user_type,user_state,register_time) values(?,?,?,?,?)";
        update(sql,userBean.getUser_name(),userBean.getUser_pwd(),userBean.getUser_type(),userBean.getUser_state(),userBean.getRegister_time());
    }
    /**
     * 根据用户名查找用户
     * @param user_name
     * @return
     */
    @Override
    public UserBean findUser(String user_name){
        String sql="select user_name,user_pwd,user_type,register_time from user where user_name=?";
        return getBean(UserBean.class,sql,user_name);
    }

    /**
     * 查看用户id为1的信息
     * @return
     */

    @Override
    public UserBean showFirstUser(){
        String sql="select user_name,user_pwd,user_type,register_time from user where user_id=1";
        return getBean(UserBean.class,sql);
    }
}

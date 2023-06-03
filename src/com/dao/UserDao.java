package com.dao;

import com.bean.UserBean;

public interface UserDao {
    void addUser(UserBean userBean);

    UserBean findUser(String user_name);

    UserBean showFirstUser();
}

package com.service;

import com.bean.UserBean;

public interface UserService {
    void addUser(UserBean userBean);

    UserBean login(UserBean userBean);
}

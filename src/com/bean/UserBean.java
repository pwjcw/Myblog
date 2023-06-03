package com.bean;

public class UserBean {
    public Integer user_id;
    public String user_name;
    public String user_pwd;
    public Integer user_type;
    public Integer user_state;
    public String register_time;


    public UserBean() {
    }

    public UserBean(Integer user_id, String user_name, String user_pwd, Integer user_type, Integer user_state, String register_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_type = user_type;
        this.user_state = user_state;
        this.register_time = register_time;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_type=" + user_type +
                ", user_state=" + user_state +
                ", register_time='" + register_time + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_state() {
        return user_state;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }
}

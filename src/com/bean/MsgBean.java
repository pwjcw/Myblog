package com.bean;

public class MsgBean {
    public Integer msg_id;
    public  String msg_title;
    public String msg_content;
    public String msg_username;
    public String msg_time;

    public MsgBean() {
    }

    public MsgBean(Integer msg_id, String msg_title, String msg_content, String msg_username, String msg_time) {
        this.msg_id = msg_id;
        this.msg_title = msg_title;
        this.msg_content = msg_content;
        this.msg_username = msg_username;
        this.msg_time = msg_time;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "msg_id=" + msg_id +
                ", msg_title='" + msg_title + '\'' +
                ", msg_content='" + msg_content + '\'' +
                ", msg_username='" + msg_username + '\'' +
                ", msg_time='" + msg_time + '\'' +
                '}';
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_username() {
        return msg_username;
    }

    public void setMsg_username(String msg_username) {
        this.msg_username = msg_username;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }
}

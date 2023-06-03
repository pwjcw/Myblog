package com.service;

import com.bean.MsgBean;

import java.util.List;

public interface MsgService {
    void addMsg(MsgBean msgBean);

    List<MsgBean> showMsg(Integer integer);

    List<MsgBean> showAllMsg();

    void deleteMsg(Integer msg_id);

    void updateMsg(MsgBean msgBean);

    MsgBean findMsg(Integer msg_id);
}

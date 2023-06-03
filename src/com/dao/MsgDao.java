package com.dao;

import com.bean.MsgBean;

import java.util.List;

public interface MsgDao {
    List<MsgBean> showMsg(Integer index);

    void addMsg(MsgBean msgBean);

    List<MsgBean> showAllMsg();

    void deleteMsg(Integer msg_id);

    void updateMsg(MsgBean msgBean);

    MsgBean findMsg(Integer msg_id);
}

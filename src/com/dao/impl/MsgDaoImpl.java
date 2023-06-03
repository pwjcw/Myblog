package com.dao.impl;

import com.bean.MsgBean;
import com.dao.BaseDao;
import com.dao.MsgDao;

import java.util.List;

public class MsgDaoImpl extends BaseDao<MsgBean> implements MsgDao {
    /**
     * 返回10条文章内容，主要是响应给前端异步加载内容
     * @param index
     * @return
     */
    @Override
    public List<MsgBean> showMsg(Integer index){
        String sql="select msg_id,msg_title,msg_content,msg_username,msg_time from msg where msg_id between ? and ?";
        return getBeanList(MsgBean.class,sql,index,index+10);
    }

    /**
     * 添加一条文章
     * @param msgBean
     */
    @Override
    public void addMsg(MsgBean msgBean){
        String sql="insert into msg (msg_title,msg_content,msg_username,msg_time) values (?,?,?,?)";
        update(sql,msgBean.getMsg_title(),msgBean.getMsg_content(),msgBean.getMsg_username(),msgBean.getMsg_time());
    }

    /**
     * 查询所有的文章信息
     * @return
     */
    @Override
    public List<MsgBean> showAllMsg(){
        String sql="select msg_id,msg_title,msg_content,msg_username,msg_time from msg";
        return getBeanList(MsgBean.class,sql);
    }

    /**
     * 删除文章
     * @param msg_id
     */
    @Override
    public void deleteMsg(Integer msg_id){
        String sql="delete from msg where msg_id=?";
        update(sql,msg_id);
    }

    /**
     * 修改文章
     * @param msgBean
     */
    @Override
    public void updateMsg(MsgBean msgBean){
        String sql="update msg  set msg_title=?,msg_content=? where msg_id=?";
        update(sql,msgBean.getMsg_title(),msgBean.getMsg_content(),msgBean.getMsg_id());
    }

    /**
     * 根据id查询一个文章的内容
     * @param msg_id
     * @return
     */
    @Override
    public MsgBean findMsg(Integer msg_id){
        String sql="select msg_id, msg_title,msg_content ,msg_username,msg_time from msg where msg_id=?";
        return getBean(MsgBean.class,sql,msg_id);
    }
}

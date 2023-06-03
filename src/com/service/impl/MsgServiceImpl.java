package com.service.impl;

import com.bean.MsgBean;
import com.dao.MsgDao;
import com.dao.impl.MsgDaoImpl;
import com.service.MsgService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MsgServiceImpl  implements MsgService {
    private MsgDao msgDao=new MsgDaoImpl();

    /**
     * 添加msg
     * @param msgBean
     */
    @Override
    public void addMsg(MsgBean msgBean){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
        String time = sdf.format(new Date());   //获取当前时间
        msgBean.setMsg_time(time);
        msgDao.addMsg(msgBean);
    }

    /**
     * 异步显示10条msg
     * @param integer
     * @return
     */
    @Override
    public List<MsgBean> showMsg(Integer integer){
        return msgDao.showMsg(integer);
    }

    /**
     * 查看所有文章
     * @return
     */
    @Override
    public List<MsgBean> showAllMsg(){
        return msgDao.showAllMsg();
    }

    /**
     * 删除文章
     * @param msg_id
     */
    @Override
    public void deleteMsg(Integer msg_id){
        msgDao.deleteMsg(msg_id);
    }

    /**
     * 修改文章
     * @param msgBean
     */
    @Override
    public void updateMsg(MsgBean msgBean){

        msgDao.updateMsg(msgBean);

    }

    /**
     * 根据id查询一个文章的内容
     * @param msg_id
     * @return
     */
    @Override
    public MsgBean findMsg(Integer msg_id){
        return msgDao.findMsg(msg_id);
    }
}

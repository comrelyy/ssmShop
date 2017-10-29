package cn.relyy.shop.bo.impl;

import cn.relyy.shop.bo.IUserBo;
import cn.relyy.shop.dao.IUserDao;
import cn.relyy.shop.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by relyy on 2017/7/15.
 */
@Service("userBo")
public class UserBoImpl implements IUserBo {

    @Resource
    private IUserDao userDao;

    @Override
    public UserVo getVoById(String id) {
       return null;
    }

    @Override
    public boolean addUser(UserVo user) throws Exception {
        user.setUserStage("Y0");    //初始化用户账号等级
       int flag = userDao.addUser(user);

       if(flag > 0){
           return true;
       }
       return false;
    }

    @Override
    public boolean checkUserName(String userName) throws Exception {

        int count = userDao.checkUserName(userName);

        if(count > 0){
            return true;
        }

        return false;
    }
}

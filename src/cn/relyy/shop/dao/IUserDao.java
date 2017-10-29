package cn.relyy.shop.dao;

import cn.relyy.shop.vo.UserVo;

/**
 * Created by relyy on 2017/7/7.
 */

public interface IUserDao {
        UserVo selectUserById();

        int addUser(UserVo user);

        int checkUserName(String userName);
}

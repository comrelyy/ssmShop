package cn.relyy.shop.bo;

import cn.relyy.shop.vo.UserVo;

/**
 * Created by relyy on 2017/7/7.
 */
public interface IUserBo {
         public UserVo getVoById(String id);

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public boolean addUser(UserVo user) throws Exception;

    /**
     * 检查用户名是否可用
     * @param userName
     * @return
     * @throws Exception
     */
    public boolean checkUserName(String userName) throws Exception;
}

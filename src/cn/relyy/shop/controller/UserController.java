package cn.relyy.shop.controller;

import cn.relyy.shop.bo.IUserBo;
import cn.relyy.shop.vo.UserVo;
import com.sun.istack.internal.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by relyy on 2017/7/26.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private IUserBo userBo;

    /**
     *注册用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/regist.action")
    public String regist(UserVo user){
        try {
            boolean flag = userBo.addUser(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("用户的注册信息是："+user.toString());
        return "login";
    }

    /**
     * 检查用户名是否被使用
     */
    @RequestMapping(method = RequestMethod.POST, value = "/checkUserName.action")
    @ResponseBody
    public  Map<String, String> checkUserName(String userName){

        Map<String, String> map = new HashMap<String, String>();
        try {
            if(userBo.checkUserName(userName)) {
                map.put("result", "fail");
            }else {
                map.put("result", "success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;

    }

}

package cn.relyy.shop.controller;

import com.sun.istack.internal.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by relyy on 2017/7/15.
 */
@Controller
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "index")
    public String loadIndexPage(){
        logger.info("跳转到首页");
        return "index";
    }

    @RequestMapping(value = "userLogin")
    public String loadLoginPage(){
        logger.info("跳转到用户登录页面");
        return "login";
    }

    @RequestMapping(value = "userRegist")
    public String loadRegistPage(){
        logger.info("跳转到用户注册页面");
        return "regist";
    }

    @RequestMapping(value = "userQuit")
    public String quit(){
        logger.info("用户退出登录，跳转到首页");
        return "index";
    }
}

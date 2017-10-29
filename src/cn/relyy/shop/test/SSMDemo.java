package cn.relyy.shop.test;

import cn.relyy.shop.bo.IUserBo;
import cn.relyy.shop.bo.impl.UserBoImpl;
import cn.relyy.shop.vo.UserVo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by relyy on 2017/7/7.
 */
public class SSMDemo {

    @Test
    public void daoTest(){
       ApplicationContext ac = new ClassPathXmlApplicationContext(".//spring-mybatis.xml");
      //  SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)ac.getBean("sqlSessionFactory");
       // SqlSession session = sqlSessionFactory.openSession();
      //  IUserDao userDao = session.getMapper(IUserDao.class);

        IUserBo userBo = (UserBoImpl)ac.getBean("userBo");
        UserVo vo = userBo.getVoById("1");
        System.out.println(vo);
    }
}

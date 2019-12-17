package pers.yo.study.Demo01_Request.example.test;

import org.junit.Test;
import pers.yo.study.Demo01_Request.example.dao.UserDAO;
import pers.yo.study.Demo01_Request.example.domain.User;

public class UserDAOTest {

    //采用Junit 来做test测试
    @Test
    public  void testLogin(){
        User userTryLogin = new User();
        userTryLogin.setUsername( "superbaby" );
        userTryLogin.setPassword( "123" );

        //操作的DAO对象来了！
        UserDAO dao = new UserDAO();
        User user = dao.login( userTryLogin );
        System.out.println( user );
    }
}
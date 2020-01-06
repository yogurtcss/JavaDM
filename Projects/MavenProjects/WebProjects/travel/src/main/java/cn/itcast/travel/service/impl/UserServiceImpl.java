package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl(); //接口回调，向上转型

    @Override //使用IDEA快速生成需重写的方法
    public boolean regist(User user) {
        /* ---1.调用dao，根据用户名查询用户
        * userFromSQL 可能存在的用户
        *  */

        /* 【坑】往后涉及到 从数据库中查询而得的“对象”rst 时，养成好习惯：
        * 先实例化一个对象A，初始化赋值为 null
        * 然后在 try...catch...中 把 从数据库中查询而得的“对象”rst 赋值给 A
        * 这样就安全了！
        *  */
        User userFromSQL = null; //先实例化一个对象A，初始化赋值为 null
        try{
            //然后在 try...catch...中 把 从数据库中查询而得的“对象”rst 赋值给 A
            userFromSQL = dao.findByUsername( user.getUsername() );
        }catch( Exception e ){
            e.printStackTrace();
        }

        if( userFromSQL!=null ){
            return false; //用户名存在，注册失败
        }

        //---2.用户名不存在时：保存用户信息
        //2.1 设置激活码：利用UUID工具类 生成UUID全局唯一字符串
        user.setCode( UuidUtil.getUuid() );
        //2.2 设置激活状态
        user.setStatus( "N" );
        //正式保存
        dao.save( user );

        //---3.向用户邮箱发送激活邮件
        /*【坑】编写邮件正文: 把用户的激活码code, [通过请求参数code] 传给activeUserServlet
        * <a href='http://localhost:8080/travel/activeUserServlet?code=XXXYYY'> 点击激活[来自黑马旅游网] </a>
        * 要带上端口号8080，否则提示找不到这个servlet！
        *  */
        String content = "<a href='http://localhost:8080/travel/activeUserServlet?code="+user.getCode()+"'>点击激活[来自黑马旅游网]</a>";
        //正式发送邮件
        MailUtils.sendMail( user.getEmail(), content, "激活邮件" );
        //返回注册标志为true
        return true;
    }

    @Override
    //激活用户: 根据激活码code找到该用户，并修改该用户的状态status为 Y；返回激活状态的标志flag --布尔值
    public boolean active(String code) {
        //---1.根据激活码查询用户对象
        User user = dao.findByCode( code );
        if( user!=null ){
            //---2.调用dao层，修改用户的激活状态为Y
            dao.updateStatus( user );
            return true;
        }
        //如果等于null，说明数据库中没有此用户，直接返回false
        return false;
    }

    @Override
    public User login(User user) {
        return( dao.findByUsernameAndPassword( user.getUsername(), user.getPassword() ) );
    }
}
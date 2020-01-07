package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---1.在登录页面，获取用户输入的数据 map_用户名Username与密码Password，各取其首字母大写 UP
        Map<String,String[]> map_UP = request.getParameterMap(); //U即用户名Username，P即密码Password，取首字母大写嗷！

        //---2.将 map_用户名与密码 封装为对象 user_umpwd  只包含用户名username、password的user对象
        User user_UP = new User(); //待填充数据的 实例对象
        try { //使用IDEA快速生成 try...catch
            BeanUtils.populate( user_UP, map_UP ); //正式填充数据
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //---3.调用service层的 findByUsernameAndPassword，通过用户名和密码 在数据库中查询有无此用户
        UserService service = new UserServiceImpl(); //接口回调，向上转型
        User userFromSQL = null; //初始化这个实例对象，以接收数据库查询的结果
        try{
            userFromSQL = service.login( user_UP ); //正式通过用户名和密码 在数据库中查询有无此用户
        }catch( Exception e ){
            //不做处理嗷
        }
        //---4.准备响应数据
        // -----判断：4.1 用户是否存在 --回写后端数据；  4.2 当用户已存在时此用户是否已激活 status='Y'---回写后端数据
        // 4.1 、4.2 需分别 回写后端数据ResultInfo嗷！

        ResultInfo info = new ResultInfo(); //在后端准备回写的数据。 稍后通过response发给前端
        /* 用于封装后端返回前端数据对象 ResultInfo，内容如下
        * private boolean flag;//后端返回结果正常为true，发生异常返回false
        * private Object data;//后端返回结果数据对象
        * private String errorMsg;//发生异常的错误消息
        *  */

        //通过用户名和密码 在数据库中查询不到此用户(结果为null)，说明用户名或密码错误了
        if( userFromSQL==null ){
            info.setFlag( false ); //发生异常
            info.setErrorMsg( "用户名或密码错误" ); //填写报错信息
        }
        //接着判断，此用户存在时，看看此用户的status是否为Y
        if( userFromSQL!=null && !userFromSQL.getStatus().equals("Y") ){ //此用户status不为Y，则未激活
            info.setFlag( false ); //发生异常
            info.setErrorMsg( "您尚未激活，请进入您的个人邮箱中点击\"激活邮件\"进行激活嗷！" ); //里面用了一个转义的双引号
        }
        if( userFromSQL!=null && userFromSQL.getStatus().equals("Y") ){ //此用户激活状态为Y，激活喽！
            info.setFlag( true ); //无异常
            //在服务器中保存此用户对象的全部信息！！注意，键名是 成功登录的用户！"user_successfulLogin"
            request.getSession().setAttribute( "user_successfulLogin", userFromSQL );
        }

        //---5.正式返回数据response
        ObjectMapper om = new ObjectMapper();
        response.setContentType( "application/json;charset=utf-8" ); //设置返回数据格式
        //om.writeValue( response.getWriter(), info ); //正式返回数据：转换为json格式，并放入流中
        String json = om.writeValueAsString( info );
        response.getWriter().write( json ); //正式返回数据
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}

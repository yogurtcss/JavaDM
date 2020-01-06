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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---0.设置编码
        //request.setCharacterEncoding( "utf-8" );
        //不用设置编码了，使用过滤器 CharsetFilter 即可自动设置编码

        //---1.验证校验
        //1.1 拿到用户填写的验证码、客户端持有的正确的验证码
        String ccode_user = request.getParameter( "check" );//用户输入的验证码 check code
        //验证码是存储在服务器的session中，所以通过session来获取服务器中正确的验证码
        HttpSession session = request.getSession();
        //在 checkcodeServlet提前设置session的attribute，键名为"CHECKCODE_SERVER"，值为当前的验证码
        String ccode_server = (String)session.getAttribute("CHECKCODE_SERVER");

        //1.2 清除上一次的验证码：获取了验证码后，过河拆桥，清除本次验证码，为了验证码只能用一次嗷
        session.removeAttribute( "CHECKCOE_SERVER" );

        //1.3 比较验证码
        if( ccode_user==null || !ccode_user.equalsIgnoreCase(ccode_server) ){ //不区分大小写比较equalsIgnoreCase
            //这是反面情况：验证码为空或验证码错误
            ResultInfo info = new ResultInfo(); //用于封装后端返回前端数据对象

            //1.3.1 注册失败时，对后端的数据对象【封装】(填充)数据
            info.setFlag( false ); //设置状态码为false
            info.setErrorMsg( "验证码错误" ); //设置错误提示信息

            //1.3.2 将封装好的对象转换为 JSON格式对象
            ObjectMapper om = new ObjectMapper(); //Jackson核心对象
            response.setContentType( "application/json;charset=utf-8" );
            //将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
            om.writeValue( response.getWriter(), info );
            return;  //这里可以不用写return了，直接就没事干了
        }

        //---2.验证码通过时，封装对象
        Map<String,String[]> map = request.getParameterMap();
        //2.1 将map封装为对象，待传入regist(User user)方法中
        User userBean = new User(); //待填充的对象
        try { //使用IDEA快速生成的try...catch
            BeanUtils.populate( userBean,map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //---3.调用service层 ，完成注册
        UserService service = new UserServiceImpl(); //接口回调 ——向上转型
        boolean flag = service.regist( userBean ); //注册是否成功的标志flag

        //---4.向后端返回数据ResultInfo info
        ResultInfo info = new ResultInfo();
        /* 还需要根据注册标志flag，填写errorMsg
        * 注册成功时，true，无错误返回信息
        * 注册失败时，false，回写错误提示信息
        *  */
        if( flag==true ){ //
            info.setFlag( true );
        }else{
            info.setFlag( false );
            info.setErrorMsg( "注册失败！" );
        }

        //正式向后端返回JSON格式数据
        ObjectMapper om = new ObjectMapper();
        //这次暂时不用 om.writeValue( response.getWriter流对象, info )这语句喽！
        String json = om.writeValueAsString( info ); //先转换为JSON格式数据

        response.setContentType( "application/json;charset=utf-8" ); //设置 数据接受格式及编码
        response.getWriter().write( json ); //正式回写数据
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}

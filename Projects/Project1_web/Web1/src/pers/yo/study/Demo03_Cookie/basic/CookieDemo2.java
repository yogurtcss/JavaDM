package pers.yo.study.Demo03_Cookie.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CookieDemo2") //为 注解的servlet模板 修改的代码，加个斜杠，不想改为首字母小写了，直接和类名相同吧！
public class CookieDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3.获取 上一次请求中 自动携带的 cookie
        Cookie[] cs = request.getCookies();

        //获取数据，遍历Cookies
        if( cs != null ){ //如果cookie实例对象不为空
            for( Cookie c : cs ){
                String name = c.getName(); //获取cookie的名
                String value = c.getValue(); //获取cookie的值
                System.out.println( name+":"+value );
            }
        }
        /* 若用另一个浏览器(如火狐)在浏览器地址栏访问/CookieDemo2，
        * 那么此新浏览器将获取不到cookie信息
        *
        * 因为这是两个不同的浏览器(相当于另外一个人与我对话)，
        * 不是处于同一个会话中。
        *  */

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}

package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*") //一个user模块对应一个servlet
/* 访问任意一个关于user模块的方法，都会被匹配到这个 UserServlet；
* 然后再到这个UserServlet中找到对应的方法来执行
*  */
public class UserServlet extends BaseServlet { //继承BaseServlet，等候方法的分发嗷！
     /* ---------------关于各模块对应的servlet下的各方法名的【修饰符】
     * protected void doPost(HttpServletRequest request, HttpServletResponse response)
     * 注意，UserServlet类下的修饰符 不要写成private，也不要写成protected！【要修改为 public】
     * 因为我要在BaseServlet中，通过【反射】 获取到 这个类UserServlet下的 任意一个方法！！
     * 若方法名的修饰符为private或protected，则我只能【暴力反射】来获取方法了！显然是不安全的！
     *  */


    /* ---------------关于【暴力反射】
    * Method[] getDeclaredMethods(); //不考虑修饰符，获取所有的成员方法（包括private修饰的）
    * Method getDeclaredMethod( String name, 类<?>... parameterTypes ); //不考虑修饰符，获取指定名字的成员方法（包括private修饰的）
    *
    * 在使用 【两个带Declared】的方法后，
    * 必须紧接着执行一个语句：
    * 此private或proteced的【Field 或Constructor 或Method 的】中间实例对象.setAccessible(true)
    * 暴力反射，忽略 “访问权限修饰符”的安全检查；
    * 这样，此 private或protected的成员方法才能被正常访问/使用
    * 否则抛出“非法访问”的异常
    * */

    public void add( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        System.out.println( "" );
    }

    public void find( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

    }




}

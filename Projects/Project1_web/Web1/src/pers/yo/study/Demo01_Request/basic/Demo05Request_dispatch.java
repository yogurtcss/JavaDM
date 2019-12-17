package pers.yo.study.Demo01_Request.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 一般在浏览器中输入网址访问资源都是通过 GET 方式(请求)，
* 所以访问此/demo05的servlet资源时时，必定执行doGet()方法
*  */

/* ----------------------------------------------------
3. 共享数据：
* 域对象：一个有作用范围的对象，可以在范围内共享数据
* request域：代表一次请求的范围，一般用于 在【使用同一个请求】转发的多个资源中 共享数据

	* 处于【同一个request域中的】 若干个servlet——它们是这个request域中的 域对象

	* 处于【同一个request域中的】域对象，它们可通过【收到的请求(即形参)的request对象】来共享同一个request域中的数据！！
	  域对象 通过 【收到的请求(即形参)的request对象】 来共享、操作同一个request域中的数据！！
	  域对象 通过 【收到的请求(即形参)的request对象】 来共享、操作同一个request域中的数据！！
	  域对象 通过 【收到的请求(即形参)的request对象】 来共享、操作同一个request域中的数据！！

* request域中的方法：
	1. void setAttribute(String name,Object obj):存储数据
		如：域对象——servletA实例对象的收到的请求request对象.setAttribute( "name", "张三嗷" );

	2. Object getAttitude(String name):通过键获取值
	3. void removeAttribute(String name):通过键移除键值对

*  */


@WebServlet("/demo5")
public class Demo05Request_dispatch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "demo5被访问了" );

        /* ▲ 注意，要在此request域中先存【共享数据】后，然后进行请求转发！！
        * 否则，若先 请求转发(这时候的request域是空数据的，传给后面servlet，后来者得到的request域也是空数据的！！)，
        * 然后再存储数据，
        * 这样后来接收转发请求的servlet就拿不到request域中的数据：显示为null
        *
        * 通过 形参-收到的请求的request实例对象 存储数据到【当前此请求的request域】中
        * 键为 msg，值为 "传过来喽！"
        *  */
        request.setAttribute( "msg", "传过来喽！" );


        //先在request域中存储了共享数据，然后进行请求转发
        request.getRequestDispatcher( "/demo6" ).forward( request, response );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 在进入 servlet 后，会执行 service(HttpServletRequest req, HttpServletResponse resp) 这个方法
        * 如果是 “GET” 请求，就会执行 doGet (req,resp) 这个方法，
        * 如果是 “POST” 请求，就会执行 doPost (req,resp) 这个方法。
        *
        * 一般在浏览器中输入网址访问资源都是通过 GET 方式(请求)，
        *    ▲ 所以访问此/demo05的servlet资源时时，必定执行doGet()方法
        *  FORM 提交中，可以通过 Method 指定提交方式为 GET 或者 POST，默认为 GET 提交
        *
        * --------------------------------------------------------------------
        * 因为 doGet()和doPost()具有通用的方法：如getParameter() ...
        * 如果使用这些通用的方法，这样 doGet()和doPost() 就有相同的代码语句(代码逻辑)了
        *
        * 如果你不确定到时servlet调用 doGet()还是doPost()，
        * 可以把代码语句(代码逻辑)全写在一个方法里(如全写在doPost方法中)，
        * 然后在另一个方法中调用这个【写有代码逻辑的方法】；
        *   如在doGet方法中通过 this.doPost( request, response )来调用doPost()方法
        *  */
        this.doPost( request, response ); //在doGet()方法中，调用doPost()方法
    }
}

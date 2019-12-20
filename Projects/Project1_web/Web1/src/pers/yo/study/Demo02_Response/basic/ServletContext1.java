package pers.yo.study.Demo02_Response.basic;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContext1")
public class ServletContext1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // svlContext 全称为 servlet Context，我简写为 svl Context了！！
        ServletContext svlContext = this.getServletContext(); //获取servlet上下文对象
        /* 三个文件新建的位置：
        * srcA.txt          -- 在Web1项目的 src文件夹下(直接在总文件夹src，不在子文件夹中)
        * webB.txt          -- 在Web1项目的 web文件夹下(直接在总文件夹web，不在子文件夹中)
        * WEB-INF_C.txt     -- 在Web1项目的 web文件夹/ WEB-INF文件夹 中
        *
        * 在第一次启动服务器时，
        * 这三个txt文件都会被复制到 项目的out输出文件夹 /artifacts /此项目名 /对应子文件夹中 ——这就是 文件在服务器中的地址 "/"
        *
        * 【文件在服务器中的地址 "/" 】  对应着 本地电脑文件夹中的路径为：【项目的out输出文件夹 /artifacts /此项目名 /对应子文件夹】
        *
        * 在Web1项目的 src文件夹下的srcA.txt，(将被复制到服务器中的地址 →) 它所在服务器中的地址为： /WEB-INF /classes 文件夹下
        * 在Web1项目的 web文件夹下的webB.txt，(将被复制到服务器中的地址 →) 它所在服务器中的地址为：/web 文件夹下
        *
        * 在Web1项目的 web文件夹/ WEB-INF文件夹 中的 WEB-INF_C.txt，
        * (将被复制到服务器中的地址 →) 它所在服务器中的地址为：/web /WEB-INF 文件夹中
        *
        *  */

        //查看 在服务器中的文件 的路径是啥 /
        String RealPathOfServer = svlContext.getRealPath( "/" ); //看看 "/"代表是啥目录
        System.out.println( RealPathOfServer );
        /* 输出 H:\ProcExes\JavaFiles\JavaDM\Projects\Project1_web\out\artifacts\Web1_war_exploded\
        * 即 此项目的输出文件夹 out \\artifacts(人工制品) \\当前的项目名_war_exploded 下
        *  */
        System.out.println( "---------------------" );
        System.out.println( svlContext.getRealPath( "/WEB-INF/classes/srcA.txt" ) );
        System.out.println( svlContext.getRealPath( "/web/webB.txt" ) );
        System.out.println( svlContext.getRealPath( "/web/WEB-INF/WEB-INF_C.txt" ) );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}

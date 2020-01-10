package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class RouteServlet extends BaseServlet { //继承基类BaseServlet
    private RouteService service = new RouteServiceImpl(); //service层
    //修饰符改为public，返回值不变，方法名为pageQuery
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //---1.接收参数
        String cidStr = request.getParameter( "cid" );
        String currentPageStr = request.getParameter( "currentPage" );
        String pageSizeStr = request.getParameter( "pageSize" ); //实际在route_list.html的前台代码中，没有传递pageSize参数！


        //---2.前端传过来的形参都是字符串型的，需处理接收的字符串参数，做强制类型转换嗷！
        //------强制类型转换cid
        int cid = 0; //类别id，整型
        if( cidStr!=null && cidStr.length()>0 && !cidStr.equals("null") ){ //判断字符串str是否为空，且字符串str长度是否大于0
            cid = Integer.parseInt( cidStr ); //将字符串型的cid转为整型
        } //cid是必需传入的！(在route_list.html中的地址栏传过来的)

        //------强制类型转换currentPage
        int currentPage = 0; //当前页码，整型，若前端没有传递过来，则默认为第一页
        if( currentPageStr!=null && currentPageStr.length()>0 ){ //判断str是否为空，且str长度是否大于0
            currentPage = Integer.parseInt( currentPageStr ); //将字符串型的currentPage转为整型
        }else{ //若前端没有把currentPageStr传递过来
            currentPage = 1; //当前页码，整型，若前端没有传递过来，则默认为第一页
        }
        //------强制类型转换pageSizeStr
        int pageSize = 0; //每页显示条数，整型，若前端没有传递过来，则默认为 每页显示5条记录
        if( pageSizeStr!=null && pageSizeStr.length()>0 ){ //判断str是否为空，且str长度是否大于0
            pageSize = Integer.parseInt( pageSizeStr ); //将字符串型的pageSize转换为整型
        }else{ //若前端没有把pageSizeStr传递过来
            pageSize = 5; //每页显示条数，整型，若前端没有传递过来，则默认为 每页显示5条记录
        }

        //---3.调用service层，查询出pageBean对象
        PageBean<Route> pb_final = service.pageQuery( cid, currentPage, pageSize );

        //---4.将数据转为JSON对象，并填入 流中
        writeValueToResponse( response, pb_final ); //BaseServlet中，自定义的方法嗷！
    }

}
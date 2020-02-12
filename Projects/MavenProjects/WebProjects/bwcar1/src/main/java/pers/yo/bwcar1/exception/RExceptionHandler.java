package pers.yo.bwcar1.exception;


import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.utils.R;

@ControllerAdvice //注解定义全局异常处理类
//请确保此 GlobalExceptionHandler 类能被扫描到并装载进 Spring 容器中。
public class RExceptionHandler {

    /* @ExceptionHandler( 自定义异常类.class 类_类型 )
    * 表示对某个异常类的处理
    *  */
    @ExceptionHandler( RException.class )
    @ResponseBody
    public R rExp( RException rException ){
        R r = new R();
        r.put( "code",500 );
        r.put( "msg", rException.getMsg() );
        return  r;
    }

    @ExceptionHandler( Exception.class )
    /* 声明了对 Exception 异常的处理，起到兜底作用，
    * 不管 Controller 层执行的代码出现了什么未能考虑到的异常，
    * 都返回统一的错误提示给客户端。
    *  */
    @ResponseBody
    public R allExp( Exception e ){
        R r = new R();
        r.put( "code", 500 );
        r.put( "msg", "系统内部异常，请联系管理员！" );
        return r;
    }

    @ExceptionHandler( AuthorizationException.class )
    public String authorExce( AuthorizationException ae ){
        return "redirect:unauthorized.html";
    }
}

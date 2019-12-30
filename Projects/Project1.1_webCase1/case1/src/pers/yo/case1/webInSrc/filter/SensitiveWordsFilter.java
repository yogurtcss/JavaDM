package pers.yo.case1.webInSrc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//这是我更改的代码模板，使用 【注解WebFilter】的简化语法 (类似Servlet注解的简化语法)
//直接填写 过滤拦截的路径，就是填写value属性 等价于 填写 urlPattern属性！
@WebFilter("/*") //这个注解表示：当【访问所有资源的路径 即/*】之前，都会执行当前这个过滤器！！
public class SensitiveWordsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取服务器资源前对请求request过滤 —— 真正地取到服务器上的资源 —— 取到服务器资源后回到过滤器 再过滤 最终返回response给浏览器
        //多个filter会依次运行，按照: 【过滤器1->过滤器2->过滤器3->程序->过滤器3->过滤器2->过滤器1】 的顺序运行。“原路返回”

        //----------1.创建动态代理类的实例对象，增强getParameter方法
        /* 真实对象-被代理的对象是ServletRequest类实例对象req，
        * 所以 动态代理类的实例对象proxy使用的就是 req的类加载器 和 req的接口数组
        *
        * 而 实例对象req已经实现的接口是 ServletRequest，(当然，动态代理类对象也实现了 【req实现的接口】)
        * 所以，可以将 req的动态代理类对象 向下转型为 ServletRequest
        *
        * 尝试把所有三个形参，全都汇总到 Proxy.newProxyInstance(...)方法中
        * 不再单独用变量分别存这三个形参了
        *  */

        /*
        ClassLoader cls = req.getClass().getClassLoader(); //动态代理类对象需要的 类加载器
        Class<?>[] interfaces = req.getClass().getInterfaces(); //动态代理类对象需要的 接口数组
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(
                cls,
                interfaces,
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                }
        );     */


        /* 尝试把所有三个形参，全都汇总到 Proxy.newProxyInstance(...)方法中
        * 不再单独用变量分别存这三个形参了
        *  */
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(
                req.getClass().getClassLoader(),
                req.getClass().getInterfaces(),
                new InvocationHandler(){
                    @Override //使用IDEA快速生成 我需要重写的方法！！
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //---------增强getParameter()方法
                        if( method.getName().equals("getParameter") ){ //判断被调用的方法是否 是getParameter
                            //----------增强返回值
                            /* 先原样调用原方法，获取原本的返回值
                            * 这句话相当于调用原方法：
                            * String value = req.getParameter("XXX");
                            * //通过键名XXX 获取到的可能是一个敏感词、脏话(如“坏蛋”)
                            *  */
                            String value = (String)method.invoke( req, args ); //先原样调用原方法，获取原本的返回值
                            if( value!=null ){ //常规操作：判空
                                //sensitiveList是本类的私有变量，在本类中可以通过 .运算符 直接访问
                                for( String oneSensitiveWord : sensitiveList ){
                                    if( value.contains(oneSensitiveWord) ){ //若value中包含敏感词，则和谐之
                                        /* public String replaceAll(String regex, String replacement)
                                        * 使用给定的 replacement 【替换】原字符串中 【与给定的正则表达式regex相匹配的】子字符串
                                        *
                                        * 这里的正则表达式regex是 “敏感词”oneSensitiveWord，将其替换为***
                                        *  */
                                        value = value.replaceAll( oneSensitiveWord, "***" );
                                    }
                                }
                            }
                            return value;
                        }
                        //还可以判断被调用的方法名是否是 getParameterMap
                        //还可以判断被调用的方法名是否是 getParameterValue

                        //其他方法则(传入原本的形参) 原样调用；此方法所属类的实例对象是 真实对象-被代理的对象req嗷！
                        return( method.invoke( req, args ) );
                    }
                }
        );

        //放行前，对request对象请求消息进行【增强处理】，并传入增强后的req
        //在这里，增强后的req为 动态代理类实例对象 proxy_req
        //执行这一句，说明放行（让下一个过滤器执行，如果没有过滤器了，就执行执行目标资源）
        chain.doFilter( proxy_req, resp );

        //放行后，对response对象的响应消息进行【增强处理】
    }

    //凡是被 private 修饰的成员变量，都称为私有变量。它只允许在本类的内部通过 "点." 直接访问，任何外部类都不能访问它。
    private List<String> sensitiveList = new ArrayList<>(); //敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        //注意：原本的敏感词sensitiveWords.txt 需【事先】以UTF-8编码格式保存！！
        try{
            //---------从 servletContext--servlet运行环境、上下文对象中.getRealPath()获取文件的真实路径

            /* //查看 MD文档  P4.3 获取 servletContext对象的汇总
            * 在过滤器中的init方法中的 config对象 获取servletContext对象
            *  */
            ServletContext servletContext = config.getServletContext();
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
            * 我佛了，我写错地址了…
            * sensitiveWords.txt文件被我放在 src/pers/yo/case1/props 文件夹下
            * 【本地电脑src目录等价于 服务器中的/WEB-INF /classes】
            * 写法为 getRealPath( "/WEB-INF/classes/ pers/yo/case1/props/sensitiveWords.txt" )
            *  */
            String realPath = servletContext.getRealPath( "/WEB-INF/classes/pers/yo/case1/props/sensitiveWords.txt" );
            //---------读取文件。  ▲ 注意：原本的敏感词sensitiveWords.txt 需【事先】以UTF-8编码格式保存！！

            /* BufferedReader( Reader r )
            * 当我们创建新的 BufferedReader 对象而不显式提供编码时，Java 默认会使用哪种编码 / 字符集？
            * BufferedReader不执行任何解码。它是另一个 Reader ... 的包装，它可以解码也可以不解码。
            * 可理解为：BufferedReader跟随FileReader进行解码！！
            * FileReader使用 JVM 的默认字符编码 GBK（由 Charset.defaultCharset()返回）【进行解码】
            *
            * FileReader 竟然没有可以指定字符编码的构造方法。
            * 而且仅仅是简单地从 父类InputStreamReader(转换流，字节流通向字符流的桥梁) 继承，并没有重写或扩展任何方法。
            * 这可能是历史上最吝啬的子类，完全就是啃老族。
            *
            * 父类InputStreamReader：是字节流与字符流之间的桥梁，
            *   能将字节流输出为字符流，并且【能为字节流指定字符集】，可输出一个个的字符；
            *
            * 在使用BufferedReader( Reader r )构造方法，传入形参Reader时：
            *   1.若已知读取的文件是GBK编码，可以 new FileReader( GBK编码的文件 ) 直接传入:
            *    - new BufferedReader( new FileReader(GBK编码的文件) );
            *   2.若读取文件不是GBK编码，不使用 FileReader 了！！
            *   则需要用 父类InputStreamReader【代替FileReader】，传入 指定字节输入流的编码！
            *    - InputStreamReader isr_utf8 = new InputStreamReader( new FileInputStream文件字节输入流( UTF8编码的文件 ), "UTF-8"指定字符集 )
            *    - new BufferedReader( isr_utf8 );
            *  */

            /* InputStreamReader isr_utf8 = new InputStreamReader( new FileInputStream(readPath), "UTF-8" );
            * 注意，第二个参数字符集 "UTF-8"可以替换为 变量StandardCharsets.UTF_8
            *  */
            InputStreamReader isr_utf8 = new InputStreamReader( new FileInputStream(realPath), StandardCharsets.UTF_8 );
            //因为原输入文件是UTF-8编码，不能直接用 FileReader读入！！
            // BufferedReader br = new BufferedReader( new FileReader(realPath) );
            BufferedReader br = new BufferedReader( isr_utf8 );

            //---------将文件中的每一行数据添加到list中
            String line = null;
            while( (line=br.readLine())!=null ){ //未读到文件末尾
                sensitiveList.add(line); //私有变量sensitiveList只允许在本类中可任意调用嗷！
            }
            br.close(); //释放资源
            System.out.println( sensitiveList );

        }catch( Exception e ){
            e.printStackTrace();
        }
    }


    public void destroy() {
    }

}

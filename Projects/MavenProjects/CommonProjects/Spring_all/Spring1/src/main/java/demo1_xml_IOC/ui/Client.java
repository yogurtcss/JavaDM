package demo1_xml_IOC.ui;

import demo1_xml_IOC.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。(更常用)
     *          - src /main /java /resources 目录下的路径，就是类路径之一
     *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）
     *
     *      AnnotationConfigApplicationContext：它是用于读取注解创建容器的，是明天的内容。
     *
     * 核心容器的两个接口引发出的问题：
     *  ApplicationContext:     单例对象适用              采用此接口
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     *
     *  BeanFactory:            多例对象使用
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     * @param args
     */
    public static void main(String[] args) {
        /* bean />标签：声明一个 bean 对象，并将该对象添加到 应用上下文——ApplicationContext实例对象 中；
        * ClassPathXml Application Context 好长啊！
        * File SystemXml Application Context 好长啊！
        *  */
        //---1.读取配置文件：获取核心容器对象(应用上下文ApplicationContext)
        ApplicationContext ac = new ClassPathXmlApplicationContext("props/bean_xml_IOC.xml"); //接口回调，向上转型
        //---2.根据id 获取bean对象
        /* Object getBean( String name  -要获取的JavaBean的名字(id值) )
         *  返回值是Object类型的，需要向下转型为自己需要的类型！
         *  */

        AccountService aService = (AccountService)ac.getBean( "accountService3" );

        /* 泛型T getBean(
        *           String name  -要获取的JavaBean的名字(id值),
        *           Class<T> requiredType  -要获取的JavaBean的类类型实例对象 -反射，类名.class属性
        * ) //传入什么Class类型，返回值就是什么类型的，不用向下转型辣！
        *  */
        //AccountDao aDao = ac.getBean( "accountDao", AccountDao.class );

        System.out.println( aService );
    }
}

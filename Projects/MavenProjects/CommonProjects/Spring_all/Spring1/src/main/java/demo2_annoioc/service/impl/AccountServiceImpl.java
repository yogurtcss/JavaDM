package demo2_annoioc.service.impl;

import demo2_annoioc.dao.AccountDao;
import demo2_annoioc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 *
 * 曾经XML的配置：
 *  <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *        scope=""  init-method="" destroy-method="">
 *      <property name=""  value="" | ref=""></property>
 *  </bean>
 *
 * 创建对象的注解：与 在XML配置文件中编写一个<bean>标签实现的功能 是一样的
 *      @ Component：把普通 pojo 实例化到 spring 容器中，相当于配置文件中的 <bean id="" class=""/>
 *          作用：把当前类对象存入spring容器中
 *          属性：value用于指定bean的id。当我们省略value时：此bean的id 默认值是当前类名，且首字母改小写。
 *
 *      ——————————————————————
 *      @ Controller：  注入service的服务，一般用在表现层      --具有属性value
 *      @ Service：     注入dao，一般用在业务层                --具有属性value
 *      @ Repository：  将dao类声明为 Bean，一般用在持久层            --具有属性value
 *      【以上三个注解的作用和属性】 与  【@Component注解的作用和属性】相同。
 *      以上三个注解是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰
 *
 *
 * 注入数据的注解 ：与 在xml配置文件中的bean标签中写一个<property>标签 的作用是一样的
 *      Autowired：——自动接线
 *          作用：自动按照【此变量 或方法的 数据类型】注入【与之相对应的变量 或方法】。
 *          //Spring的IOC容器：Map结构，键是注解的value，值是注解生成的bean对象
 *
 *          在 Spring应用上下文 “容器” 中的 “值”-bean对象 中寻找 【需要的bean对象】(而不是找键key)：
 *          只要容器中【有唯一的bean对象数据类型】和 【要注入的变量或方法 的数据类型】 相匹配，就可以注入成功
 *                -如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *                -如果ioc容器中有多个类型匹配时：先按【数据类型】筛选出可能匹配的 变量或对象，
 *                                             然后 按 被注入的变量或方法【名字】与bean的id 进行匹配：
 *                                               -若 被注入的变量名或方法名 === 某个bean的id，则匹配成功，就注入成功
 *          出现位置：可以是变量上，也可以是方法上
 *
 *          细节：@Autowired 后不需要 getter()和 setter()方法，Spring 也会自动注入
 *
 *      Qualifier:
 *          作用：在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以（稍后我们讲）
 *          属性：value：用于指定注入bean的id。
 *
 *      Resource
 *          作用：直接按照bean的id注入。它可以独立使用
 *          属性：name：用于指定bean的id。
 *
 *      以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *      另外，集合类型的注入只能通过XML来实现。
 *
 *      Value
 *          作用：用于注入基本类型和String类型的数据
 *          属性：value：用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式）
 *              SpEL的写法：${表达式}
 *
 *
 * 用于改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope
 *          作用：用于指定bean的作用范围
 *          属性：value：指定范围的取值。常用取值：singleton prototype
 *
 *
 * 和生命周期相关 了解
 *      他们的作用就和在bean标签中使用init-method和destroy-method的作用是一样的
 *      PreDestroy
 *          作用：用于指定销毁方法
 *      PostConstruct
 *          作用：用于指定初始化方法
 */

//@Component("accountService") // 类似@WebServlet("/user") 省略value= 的最简化写法！
// @Component注解的属性：value用于指定bean的id。当省略value时：此bean的id 默认值是当前类名，且首字母改小写。
@Service( "accountService" )
public class AccountServiceImpl implements AccountService {

    @Autowired //自动注入
    private AccountDao accountDao1;
    /* 我已在 AccountDaoImpl1 --dao的实现类 加了注解 @Repository("accountDao1")
    * 同时，我也在 AccountDaoImpl2 --dao的实现类上，也加了注解 @Repository("accountDao2")
    *
    * @Autowired 注解会 匹配 并注入哪个bean呢？
    * -如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
    * -如果ioc容器中有多个类型匹配时：先按【数据类型】筛选出可能匹配的 变量或对象，
    *                              然后 按 被注入的变量或方法【名字】与bean的id 进行匹配：
    *                              -若 被注入的变量名或方法名 === 某个bean的id，则匹配成功，就注入成功
    *
    * 在这里，已有的bean的id为：accountDao1、accountDao2
    * 若我待注入的对象名为 accountDao1，恰好与某个已有的bean的id相等
    * 则此对象自动注入成功！
    *
    * //此时，若对象名改为 accountDao，则会报错！
    *  */

    @Override
    public void saveAccount() {
        accountDao1.saveAccount();
    }
}
package demo1.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory2 {
    /* properties集合中：
     * 键：类的名称，如accountDao
     * 值：某个类的【全限定类名】 包名.类名 demo1.dao.impl.AccountDaoImpl
     *  */
    private static Properties props; //定义一个Properties实例对象
    /* Map集合中：——称之为 “容器”
     * 键：类的名称
     * 值：此类对应的 实例对象Object
     *  */
    private static Map<String, Object> beansMap;

    static{
        try{
            //实例化 props集合
            props = new Properties();
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream( "props/bean.properties" );
            System.out.println( is==null );
            /* properties集合中：
             * 键：类的名称，如accountDao
             * 值：某个类的【全限定类名】 包名.类名 demo1.dao.impl.AccountDaoImpl
             *  */
            props.load( is );

            //实例化 map容器
            beansMap = new HashMap<>();
            /* 取出properties集合中的所有键名：
             * 枚举中的泛型 是Object类型，不应只写Object，应该写全类名 java.lang.Object
             * 因为 org.omg.CORBA包下的 Object 是接口！ org.omg.CORBA.Object
             * 如果只写 Object，
             * IDEA不知道你所指的Object是 java.lang.Object类 还是 org.omg.CORBA.Object接口！
             *  */
            Enumeration<?> keys = props.keys();
            while( keys.hasMoreElements() ){
                /* 取出每个key：类的名称
                 * keys.nextElement() 返回的是Object类型，
                 * 需向下转型为String：  (String)keys.nextElement()
                 * 或直接变为String型：  keys.nextElement().toString()
                 *  */
                String key = keys.nextElement().toString(); //key是 类的名称
                System.out.println( key );
                //根据key获取value
                String beanPath = props.getProperty( key );
                System.out.println( beanPath );
                /* 由反射reflect：全类名的方法Class.forName("全类名") 创建实例对象obj_reflect：
                 * 需强制类型转换为 Object类型！
                 *  */
                Object obj_reflect = Class.forName( beanPath ).newInstance();
                /* Map集合中：——称之为 “容器”
                 * 键：类的名称
                 * 值：此类对应的 实例对象Object
                 *  */
                beansMap.put( key, obj_reflect );
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    //由工厂生成出来的 实例对象是 Object类型的，需要向下转型为自己要的类型嗷！
    public static Object getBean( String beanName ){
        //直接在 “容器” beansMap中，根据 传入的类名(键名) 拿到这个实例对象
        return( beansMap.get(beanName) );
    }
}

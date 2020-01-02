package pers.yo.study.Demo07_AjaxJson.解析器Jackson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pers.yo.study.Demo07_AjaxJson.解析器Jackson.domain.Person;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*  JacksonTest1的 JUnit单元测试
1.把jackson的相关jar包 导入至 web/WEB-INF/lib文件夹下！！



*  */


public class JacksonTest1 {

    @Test
    public void test1() throws IOException { //Java对象转换为Json字符串
        //----------1.创建Person对象
        Person p = new Person();
        p.setName( "张三" );
        p.setAge( 23 );
        p.setGender( "男" );

        //----------2.创建Jackson的核心对象 ObjectMapper
        ObjectMapper om = new ObjectMapper();

        //----------3.正式：将Java对象转换为Json字符串
        /* 转换方法：
        (1)writeValue(参数1，obj):
           参数1：
              File：将obj对象转换为JSON字符串，并保存到指定的文件中
              Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
              OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中

        (2)writeValueAsString(obj): 将对象转为json字符串
        * */
        String json = om.writeValueAsString( p );
        System.out.println( json );

        /* writeValue，将数据写到H:\Desktop\work\JacksonTest1.txt中
        * 注意，win10的路径分隔符是 \
        * 在Java代码中，要转义为 \\
        *  */
        om.writeValue( new File("H:\\Desktop\\work\\JacksonTest1.txt"), p );


        /* FileWriter类从 OutputStreamWriter 类继承而来，
        * FileWriter类类按字符向流中写入数据
        * FileWriter(File文件对象 file)
        *  */
        //om.writeValue( new FileWriter( new File("H:\\Desktop\\work\\JacksonTest1.txt") ), p );
    }

    @Test
    public void test2() throws JsonProcessingException {
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString( p );
        System.out.println( json );
    }

    @Test
    public void test3() throws JsonProcessingException {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(23);
        p1.setGender("男");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(23);
        p2.setGender("男");
        p2.setBirthday(new Date());

        List<Person> ps = new ArrayList<>();
        ps.add( p );
        ps.add( p1 );
        ps.add( p2 );

        //2.转换
        ObjectMapper om = new ObjectMapper();
        //writeValueAsString(obj): 将对象转为json字符串
        String json = om.writeValueAsString( ps );
        /* [  {"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"},
              {"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"},
              {"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"}    ]
        *
        *  */
        System.out.println( json );
    }


    @Test
    public void test4() throws JsonProcessingException {
        //1.创建map实例对象
        Map< String,Object > map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        map.put("gender","男");

        //2.转换
        String json = new ObjectMapper().writeValueAsString( map );
        System.out.println( json );
    }


    @Test
    public void test5() throws IOException {
        /* 创建Json字符串
        * 注意：在字符串内容中添加双引号，需要使用 转义 \"
        * 注意：   \" 就表示 单边的双引号 " 嗷！
        *  */
        String json = "{  \"gender\":  \"男\" ,  " +
                      "    \"name\" :  \"张三\",  " +
                      "    \"age\":    23     }";

        //创建核心对象
        ObjectMapper om = new ObjectMapper();
        /* 将Json字符串转为 Person实例对象
        * ObjectMapper实例对象om.readValue( json字符串, T类型.class );
        * 返回值为 T类型的实例对象t
        *  */
        Person p = om.readValue( json, Person.class );
        System.out.println( p );

    }

}

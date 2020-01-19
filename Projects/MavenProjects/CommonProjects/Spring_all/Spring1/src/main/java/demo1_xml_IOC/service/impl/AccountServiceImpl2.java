package demo1_xml_IOC.service.impl;


import demo1_xml_IOC.service.AccountService;

import java.util.Date;

public class AccountServiceImpl2 implements AccountService {
    //private AccountDao dao;

    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

//    public AccountServiceImpl(){
//        System.out.println( "对象创建辽！" );
//    }

    //在 bean_xml_IOC.xml 配置<bean />标签前，先在本类中提供这些setter方法
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        //dao.saveAccount();
        System.out.println("service中的saveAccount方法执行了。。。"+name+","+age+","+birthday);
    }



    @Override
    public String toString() {
        return "AccountServiceImpl2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}

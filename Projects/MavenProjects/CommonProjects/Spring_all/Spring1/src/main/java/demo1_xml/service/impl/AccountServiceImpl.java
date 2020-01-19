package demo1_xml.service.impl;


import demo1_xml.service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {
    //private AccountDao dao;

    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;
    private int INT;

//    public AccountServiceImpl(){
//        System.out.println( "对象创建辽！" );
//    }

    //在 bean_xml.xml 配置<bean />标签前，先在这个类中提前写好带参的构造方法
    public AccountServiceImpl( String name, Integer age, Date birthday, int INT ){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.INT = INT;
    }

    public void saveAccount() {
        //dao.saveAccount();
    }

    @Override
    public String toString() {
        return "AccountServiceImpl{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", INT=" + INT +
                '}';
    }
}

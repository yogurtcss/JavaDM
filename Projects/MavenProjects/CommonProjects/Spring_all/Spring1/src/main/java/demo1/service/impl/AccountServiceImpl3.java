package demo1.service.impl;


import demo1.service.AccountService;

import java.util.*;

public class AccountServiceImpl3 implements AccountService {
    //private AccountDao dao;

    //如果是经常变化的数据，并不适用于注入的方式
    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String,String> myMap;
    private Properties myProps;

//    public AccountServiceImpl(){
//        System.out.println( "对象创建辽！" );
//    }

    //在 bean.xml 配置<bean />标签前，先在本类中提供这些setter方法
    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    @Override
    public void saveAccount() {
        //dao.saveAccount();
        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myProps);
    }

    @Override
    public String toString() {
        return "AccountServiceImpl3{" +
                "myStrs=" + Arrays.toString(myStrs) +
                ", myList=" + myList +
                ", mySet=" + mySet +
                ", myMap=" + myMap +
                ", myProps=" + myProps +
                '}';
    }
}

package pers.yo.adv1.demo25_Junit.test;

/* Junit单元测试

一、测试分类
1.黑盒测试：不需要写代码，给输入值，看程序是否能够输出期望的值
2.白盒测试：需要写代码，需关注程序具体的执行流程

二、Junit的使用：属于白盒测试
* 步骤：
1.定义一个测试类(测试用例)；格式建议如下：
    * 测试类名：被测试的类名Test， 如 CalculatorTest
    * 包名：xxx.xxx.xx.test，    如 pers.yo.xxx.test

2.定义(若干个)测试方法
——这是将来可独立运行的(导包org.junit.Test、为方法名加标识@Test即可)；

格式建议如下
    * 方法名：test测试的方法名，   如 testAdd()
    * 方法的返回值类型：void
    * 输入的参数列表：空参

3.导包org.junit.Test、为方法名加标识@Test即可

* 判定结果：
    * 红色：失败，没有得到预期的结果
    * 绿色：成功，得到了预期的结果
    一般使用断言操作来判断结果 (需导包 org.junit.Assert)
    Assert.assertEquals( long expected期望值, long actual由程序计算得到的真实值 )

--------------------------------------------------
补充
Before，需导包 org.junit.Before
   * 初始化方法
   * 用于资源申请，所有测试方法在执行之前，都会自动执行此Before标识的方法

After，需导包 org.junit.After
   * 释放方法
   * 用于资源释放，所有测试方法在执行完毕，都会自动执行此After标识的方法
   * 注：无论该方法是 测试通过或不通过，最终均会自动运行此After 释放资源的方法

*  */

import org.junit.Before;
import org.junit.After;

import org.junit.Test;
import org.junit.Assert; //断言，判断期望值与真实值是否相等

import pers.yo.adv1.demo25_Junit.Calculator;

public class CalculatorTest {

    @Before
    /* Before，需导包 org.junit.Before
    * 初始化方法
    * 用于资源申请，所有测试方法在执行之前，都会自动执行此Before标识的方法
    *  */
    public void init(){
        System.out.println( "init..." );
    }

    @After
    /* After，需导包 org.junit.After
     * 释放方法
     * 用于资源释放，所有测试方法在执行完毕，都会自动执行此After标识的方法
     * 注：无论该方法是 测试通过或不通过，最终均会自动运行此After 释放资源的方法
     *  */
    public void close(){
        System.out.println( "close..." );
    }


    @Test   //导包org.junit.Test，加@Test标识：标识此方法可以(不通过main方法)独立运行
    public void testAdd(){
        System.out.println( "我被执行辣！" );
        System.out.println( "testAdd..." );
        Calculator c = new Calculator();
        int rst = c.add(1,2);
        /* 一般不会在单元测试里面输出测试结果，因为 能看到绿色的输出结果，并不代表 这是符合预期的结果
        * 所以可能会输出 不合逻辑的、且不符合预期的结果
        *  */
        //System.out.println( rst );

        /* 断言：我断言这个结果是3
        * 需要导包 org.junit.Assert
        * Assert.重载的方法assertEquals( long expected你期望的值 , long actual真实值：由程序计算出来的rst )
        * 注：long长整型 可包含 int整型
        *  */
        Assert.assertEquals( 4, rst );
        //注：无论该方法是 测试通过或不通过，最终均会运行此After 释放资源的方法
    }

    @Test
    public void testSub(){
        Calculator c = new Calculator();
        int rst = c.sub( 1,2 );
        Assert.assertEquals( -1, rst );
    }

    @Test
    public void testMulti(){
        Calculator c = new Calculator();
        int rst = c.multi( 2, 3 );
        Assert.assertEquals( 6, rst );
    }
}
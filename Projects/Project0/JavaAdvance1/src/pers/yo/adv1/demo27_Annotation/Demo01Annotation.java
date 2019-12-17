package pers.yo.adv1.demo27_Annotation;

/**
 * 注解javadoc演示
 *
 * @author yogrt
 * @version 1.0
 * @since 1.5 表示从JDK1.5后，可以使用我这个类
 */

public class Demo01Annotation {
    // 快速敲入 /** 然后回车，即可快速、智能地添加【与该代码块相关】的注解
    /**
     * 计算两数之和
     * @param a 整型
     * @param b 整型
     * @return a+b 整型
     */
    public int add( int a, int b ){
        return a+b;
    }
}

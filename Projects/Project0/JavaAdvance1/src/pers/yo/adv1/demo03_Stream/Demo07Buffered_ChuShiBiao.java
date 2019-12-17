package pers.yo.adv1.demo03_Stream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class Demo07Buffered_ChuShiBiao {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader( new File("E:\\Data\\ChuShiBiao_Disorder.txt") ); //disorder 无序的
        BufferedReader br = new BufferedReader( fr );

        HashMap<Integer,String> numSentences = new HashMap<>(); //num序号-sentences句子
        String oneLine;
        /* 【坑2】 while循环中：
        * 先执行里面这赋值语句oneLine=br.readLine()-此时已经把读取的一行放进外部变量oneLine了！
        * 然后再判断是否为空！！这样能确保所有的行都能被读取放入变量oneLine中！！
        *
        * 若跳步了：
        * while( (oneLine=br.readLine())!=null ) { //while这里的readLine读到了A行
        *   System.out.println( oneLine=br.readLine() ); //这里的oneLine=br.readLine() 读取的是 【A的下一行】！！不是原本的A行！！
        *   //所以，在while 的条件中，就把读取的每一行存进外部变量oneLine，这样能确保所有的行都能被读取放入变量oneLine中！！
        *   //这里，类似于 迭代器的next()，不能跳步！
        * }
        *
        *  */

        //String readLine()——读取一个文本行。读取一行数据，返回值为 字符串
        while( (oneLine=br.readLine())!=null ){ //【坑1】读取每一行，使用String readLine()——读取一个文本行。读取一行数据，而不是read(缓冲区数组)！！
            //若用了read(缓冲区数组)方法，(不是逐行地读进来)，是一下子把所有文字(作为一大坨东西)读进来！！！这样就取不了原本各行的首元素(序号)了

            char[] oneLineChars = oneLine.toCharArray(); //字符串实例对象str.toCharArray()，将字符串转为字符数组【容易忘】
            /* 【坑3】 如何把 char ‘9’ 转为 int 9？
            * 不能直接对此字符做强制类型转化： (int) ch字符！！——这样得到是 ‘9’ 的 Asc II码 (而不是 整型的9)！！
            *
            * 解决：使用char的包装类Character的静态方法：get  Numeric数字  Value
            * Character.getNumericValue 将字符数字，转为原原本本的整型数字
            * 即可把字符9 转换为 整型9
            *  */
            int oneLineNum = Character.getNumericValue( oneLineChars[0] ) ;//每一行的序号；不能直接用int强制类型转换！！要用包装类Character的静态方法getNumericValue
            String oneLineSentence = new String( oneLineChars, 2, oneLineChars.length-2 ); //每一行的文字内容：字符数组截取法生成字符串，下标从2开始，截取长度为oneLineChars.length-2
            numSentences.put( oneLineNum, oneLineSentence );
        }
        //取出所有的键值对
        Set< Map.Entry<Integer,String> > kv = numSentences.entrySet();
        Iterator< Map.Entry<Integer,String> > itKV = kv.iterator();
        //开始把数据写到文件中
        FileWriter fw = new FileWriter( "E:\\Data\\ChuShiBiao_Ordered.txt" ); //ordered 排好序的
        BufferedWriter bw = new BufferedWriter( fw );

        while( itKV.hasNext() ){
            Map.Entry<Integer,String> oneKeyValue = itKV.next();
            System.out.println( oneKeyValue );
            /* 这里输出的结果，居然是 已排好序的1-9的结果！
            * 用的 hashSet 采用的 hash 算法的缘故，刚好把 1-9 给映射到 1-9，然后放到指定的位置，
            * 取出来迭代时则正好看到的就是 1-9。但是，就算是这一点，也是不保证的。【纯属巧合】
            * 因为 hash 函数不一定就是这个行为，容器不保证这一点，而且先后放，冲突的处理也是不保证用什么方法。
            *  */
            bw.write( oneKeyValue.getKey()+"."+oneKeyValue.getValue()+"\n" );
            bw.flush(); //把缓冲区中的数据，强制刷新到文件中；写一次刷一次
        }
        //先打开的后关闭，后打开的先关闭
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
}

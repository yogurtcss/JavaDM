package pers.yo.adv1.demo14_Map;

/* Map集合练习：
* 计算一个字符串中，每个字符出现的次数
* 思路：使用HashMap集合，键为某字符c，值为该字符出现的次数count
*  */


import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class CharInString {
    public void count( String s ){
        /* 字符串与字符数组的相互转换，这里我有点忘了
           char[]  a = { 'a', 'b ', 'x'};
           String  s = new String(a);   //字符数组转为字符串
           char[]  c = s.toCharArray();  //字符串转换成字符数组
        *
        *  */
        char[] c = s.toCharArray();
        HashMap<Character,Integer> charCount = new HashMap<>(); //泛型必须使用包装类
        for( char oneChar : c ){
            charCount.put( oneChar, 1 );
        };

        HashMap<Character, Boolean> isFinish = new HashMap<>();
        for( char oneChar : c ){
            isFinish.put( oneChar, false ); //某字符 是否已统计完成一轮了
            /* 某字符 是否已统计完成一轮了
            * 默认为false：未统计完成一轮；
            * true：已统计完成一轮；【在for循环中，应避开这些已统计过的字符：不要遍历这些重复的字符！！】
            *  */
        };

        /* 是否已统计完成一轮 的含义：
        * 固定某一字符c，让c与 【c后面的字符】 逐一比较，并记录c【重复出现】的次数count；
        * 当比较(记录)到最后一个字符时，认定 此c字符 “已统计完成一轮”
        *
        * 易知，当某字符c统计完成一轮时，此【c字符的重复出现次数】也已记录完毕了；
        * 则在for循环中，应该避开 这些已统计过的字符：不要遍历这些重复的字符！！
        *  */

        for( int i=0, length=c.length; i<length; i++ ){
            if( isFinish.get(c[i])==false ){ // 【判断：是否重复遍历的标记】：当前待遍历的字符 “是否已统计完成一轮”， 若未统计完成一轮，则继续往下走
                for( int j=i+1; j<length; j++ ){
                    if( c[i]==c[j] ){ //在一次统计中，若出现相同的字符，此字符的出现次数count+1
                        int old = charCount.get( c[i] );
                        int newValue = old + 1; //若出现相同的字符，此字符的出现次数count+1
                        charCount.put( c[i],newValue );
                    }
                    if( j==length-1 ){ //当某字符比较(统计)完全一轮后：修改此字符c的完成标记为true，这样在for循环中就不会遍历到此字符了
                        isFinish.remove( c[i] ); //移除此字符的 原标记false
                        isFinish.put( c[i],true ); //修改此字符c的完成标记为true，这样在for循环中就不会遍历到此字符了
                    }
                }
            }
        }

        //使用迭代器 迭代Map.Entry<K,V> 键值对的集合
        System.out.println( "----------我用：使用迭代器 迭代Map.Entry<K,V> 键值对的集合----------" );
        Set< Map.Entry<Character,Integer> > kvCharCount = charCount.entrySet();
        Iterator< Map.Entry<Character,Integer> > itKV = kvCharCount.iterator();
        while( itKV.hasNext() ){
            Map.Entry<Character,Integer> onekv = itKV.next();
            System.out.println( onekv.getKey()+"："+onekv.getValue()+" 次" );
        }

        keySet_for( charCount );
        keySet_it( charCount );

    }

    public void keySet_for( HashMap<Character,Integer> map ){ //增强for循环，遍历键的集合keySet
        System.out.println( "----------我用：增强for循环，遍历键的集合keySet----------" );
        Set<Character> keys = map.keySet();
        for( char oneKey: keys ){
            System.out.println( oneKey+"："+map.get(oneKey)+" 次" );
        }

    }
    public void keySet_it( HashMap<Character,Integer> map ){  //迭代器迭代，遍历键的集合keySet
        System.out.println( "----------我用：迭代器迭代，遍历键的集合keySet----------" );
        Set<Character> keys = map.keySet();
        Iterator<Character> it = keys.iterator();
        while( it.hasNext() ){
            char oneKey = it.next();
            System.out.println( oneKey+"："+map.get(oneKey)+" 次" );
        }
    }




}

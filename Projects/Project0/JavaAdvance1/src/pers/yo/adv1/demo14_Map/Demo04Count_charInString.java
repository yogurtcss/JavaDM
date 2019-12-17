package pers.yo.adv1.demo14_Map;

/* Map集合练习：
* 计算一个字符串中，每个字符出现的次数
* 思路：使用HashMap集合，键为某字符c，值为该字符出现的次数count
*  */

import pers.yo.adv1.demo14_Map.CharInString;

public class Demo04Count_charInString {
    public static void main(String[] args) {
        String s = "abbacabbad"; //a:4次， b:4次， c:1次， d:1次
        CharInString c = new CharInString();
        c.count( s );

    }
}

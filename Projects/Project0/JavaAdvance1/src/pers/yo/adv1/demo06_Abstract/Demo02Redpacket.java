package pers.yo.adv1.demo06_Abstract;

import java.util.ArrayList;
import pers.yo.adv1.demo06_Abstract.Manager;
import pers.yo.adv1.demo06_Abstract.Member;

public class Demo02Redpacket {
    public static void main(String[] args) {
        Manager m = new Manager( "群主", 100 );

        Member a = new Member( "A", 0 );
        Member b = new Member( "B", 0 );
        Member c = new Member( "C", 0 );
        System.out.println( "初始时，未发红包嗷：" );
        m.show();
        a.show();
        b.show();
        c.show();
        System.out.println( "------------------------" );
        System.out.println( "👴发红包喽！" );
        ArrayList<Integer> redList = m.send( 20, 5 );
        //成员收红包
        a.receive( redList );
        b.receive( redList );
        c.receive( redList );

        m.show();
        a.show();
        b.show();
        c.show();
    }
}

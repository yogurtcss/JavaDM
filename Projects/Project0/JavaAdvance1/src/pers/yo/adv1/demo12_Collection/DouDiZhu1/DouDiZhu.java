package pers.yo.adv1.demo12_Collection.DouDiZhu1;

/* 斗地主综合案例
* 1.准备牌【略难。花色与点数自由组合】
* 2.洗牌
* 3.发牌【难。发牌时，取模3？】
* 4.看牌
*
* -------------------------------------
* 1.准备牌
* ▲ 数组/集合：4种花色 ♠、♥、♣、♦
* ▲ 数组/集合：13个编号大小 A,2,3,...,10,J,Q,K
* 两重for循环解决
*
* -------------------------------------
* 2.洗牌 - 洗的是编号
* ▲ 集合工具类Collections中，全是静态方法
*
* 洗牌操作： static void shuffle( List<?> list )
* 使用默认随机源对指定列表进行置换
* 人话：将原集合中的元素 按默认的随机规则，进行随机排序
*
* -------------------------------------
* 3.发牌
* 一人一张轮流发牌【怎么发？】，剩余3张作为底牌；
* 每人拿17张牌
*
* 一人一张轮流发牌【怎么发？】：每张牌的下标÷3，取余数 (即 取模3)
* 注意，发牌流程是 “环回”地发牌：按顺序 将某张牌发至第3人时，下一张牌又发回给第1人
* 按余数发牌，恰好是 “环回”的。
*
* 当发一张牌x1(下标为0)时，x1有3种选择 ( 下标0-玩家1、下标1-玩家2、下标2-玩家3 )
*
* 设 牌的序号(第几张牌) 除以3 的商为c，则商c表示 第c轮的发牌
* 设 牌的序号(第几张牌) 除以3 的余数为r，则r表示 在该第c轮发牌中，要把此牌发给 下标为r的玩家
*
* 第0轮的发牌 —— 商c=0
* 0÷3=0 余 0，就把 第0张牌 发给 下标0的玩家1
* 1÷3=0 余 1，就把 第1张牌 发给 下标1的玩家2
* 2÷3=0 余 2，就把 第2张牌 发给 下标2的玩家3
*
* 第1轮的发牌 —— 商c=1
* 3÷3=1 余 0，就把 第3张牌 发给 下标0的玩家1
* 4÷3=1 余 1，就把 第4张牌 发给 下标1的玩家2
* 5÷3=1 余 2，就把 第5张牌 发给 下标2的玩家3
*
* ...
*
* -------------------------------------
* 4.看牌
* 直接打印集合，for循环，输出各玩家持牌的集合，输出底牌的集合
*
*  */

import java.util.ArrayList;
import pers.yo.adv1.demo12_Collection.DouDiZhu1.PokerGame;

public class DouDiZhu {
    public static void main(String[] args) {
        //新建玩家1、2、3和底牌
        ArrayList<String> p1_pokers = new ArrayList<>();
        ArrayList<String> p2_pokers = new ArrayList<>();
        ArrayList<String> p3_pokers = new ArrayList<>();
        ArrayList<String> diPai = new ArrayList<>();

        PokerGame pg = new PokerGame(); //新建一场扑克游戏
        ArrayList<String> pokers = pg.preparePokers(); //准备牌：花色与点数自由组合中
        pg.shufflePokers( pokers ); //洗牌
        pg.dispatchPokers( pokers, p1_pokers, p2_pokers, p3_pokers, diPai  ); //发牌：取模3

        //为牌组排序后，然后再看牌
        pg.sortPokers( diPai );
        pg.sortPokers( p1_pokers );
        pg.sortPokers( p2_pokers );
        pg.sortPokers( p3_pokers );

        //查看牌组
        pg.showPokers( "底牌", diPai );
        pg.showPokers( "玩家1", p1_pokers );
        pg.showPokers( "玩家2", p2_pokers );
        pg.showPokers( "玩家3", p3_pokers );
    }
}

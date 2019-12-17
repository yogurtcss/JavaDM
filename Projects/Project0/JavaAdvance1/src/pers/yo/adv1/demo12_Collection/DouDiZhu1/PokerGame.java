package pers.yo.adv1.demo12_Collection.DouDiZhu1;


import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    public ArrayList<String> preparePokers(){ //准备牌
        String[] color = new String[]{ "♠","♥","♣","♦" };
        String[] number = new String[]{ "A","2","3","4","5","6","7","8","9","10","J","Q","K" };
        ArrayList<String> pokers = new ArrayList<>();
        for( String oneColor: color ){
            for( String oneNumber: number ){
                pokers.add( oneColor+oneNumber );
            }
        }
        //忘记加入大王和小王了
        pokers.add( "🃏大王" );
        pokers.add( "🃏小王" );

        return pokers;
    }

    //洗牌
    public void shufflePokers( ArrayList<String> pokers ){ //洗牌操作，直接调用 Collections.shuffle( 牌 )
        Collections.shuffle( pokers );
    }

    public void dispatchPokers( ArrayList<String> pokers, ArrayList<String> p1_pokers, ArrayList<String> p2_pokers, ArrayList<String> p3_pokers, ArrayList<String> diPai ){ //发牌
        for( int i=0, length=pokers.size(); i<length; i++ ){
            String onePoker = pokers.get(i);
            if( i<51 ){ //发前51张牌
                if( i%3==0 ){
                    p1_pokers.add(onePoker);
                }
                else if( i%3==1 ){
                    p2_pokers.add(onePoker);
                }
                else{
                    p3_pokers.add(onePoker);
                }
            }
            else{ //剩余3张留作底牌
                diPai.add(onePoker);
            };
        }

    }

    public void showPokers( String who, ArrayList<String> onePokers ){ //看牌
        System.out.print( "查看当前 "+who+" 的牌组为：[ " );
        for( int i=0, length=onePokers.size(); i<length; i++ ){
            if( i<length-1 ){
                System.out.print( onePokers.get(i) + ", " ); //onePokers.get(i) 第i张牌
            }
            else{
                System.out.println( onePokers.get(i) + " ]" );
            }
        }
    }

    public void sortPokers( ArrayList<String> onePokers ){ //给牌进行排序(可以先排序，后看牌)
        Collections.sort( onePokers );
    }

}

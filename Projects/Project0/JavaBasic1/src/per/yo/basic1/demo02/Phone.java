package per.yo.basic1.demo02;

public class Phone {
    String brand; //品牌
    double price; //价格
    String color; //颜色

    public void call( String who ){
        System.out.println( "I call  " + who );
    }
    public void sendMsg(){
        System.out.println( "群发嗷" );
    }
}

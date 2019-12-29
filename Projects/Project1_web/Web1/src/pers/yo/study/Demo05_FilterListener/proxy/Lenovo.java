package pers.yo.study.Demo05_FilterListener.proxy;
import pers.yo.study.Demo05_FilterListener.proxy.SaleComputer;

public class Lenovo implements SaleComputer { //真实的类
    @Override //使用IDEA 快速生成需重写的方法嗷！
    public String sale(double money) {
        System.out.println( "花了 "+money+" 元买了一台联想电脑！" );
        return "联想电脑！";
    }

    @Override
    public void show() {
        System.out.println( "展示电脑..." );
    }
}

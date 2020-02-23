package pers.yo.utils;

public class ThreadUtil { //线程工具类。爬取一下，就休息一下

    @SuppressWarnings( "static-access" ) //添加一个警告
    public static void sleep( long millions ){ //传入形参 millions毫秒
        try {
            Thread.currentThread().sleep( millions );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* 测试一下，随机休眠的秒数
    ThreadUtil.sleep( //爬取一下，我就休息几秒
        //Long.parseLong( LoadConfigPropertiesUtil.getPropsFromConfigByKeyName("millions") )
        /* 随机的秒数
        * 因为 Long.Long.parseLong( 形参String )，形参要求是String型，
        * 而与 Math.random()相乘，又得是整型，所以最终的写法如下！
        /
        (long)(Math.random() * Integer.parseInt(millions_str))
    );

    * */
    public static void main(String[] args) {
        //测试一下，随机休眠的秒数，单位：毫秒
        String millions_str = LoadConfigPropertiesUtil.getPropsFromConfigByKeyName("millions");
        for( int i=0; i<=10; i++ ){
            System.out.println( (long)(Math.random() * Integer.parseInt(millions_str)) );
        }
    }
}

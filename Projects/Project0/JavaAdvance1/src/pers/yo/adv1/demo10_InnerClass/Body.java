package pers.yo.adv1.demo10_InnerClass;

public class Body { //外部类
    public class Heart{ //内部类
        public void beat(){ //内部类方法
            System.out.println( "我是内部类方法beat！心脏跳动！" );
            System.out.println( "我在内部类中调用 外部类的私有变量："+name );
        }
    };

    private String name; //外部类的【私有】成员变量，它也可以被内部类使用!

    public void methodBody() { //外部类方法
        System.out.println( "我是外部类方法嗷！" );
        /* 套娃过程：
         * 创建内部类的实例对象inner，并在此调用inner的方法
         *  */
        Heart h = new Heart();
        h.beat();
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

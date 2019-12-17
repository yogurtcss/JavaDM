package pers.yo.adv1.demo03_Stream;

/*
    序列化和反序列化的时候,会抛出NotSerializableException没有序列化异常
    类通过实现 java.io.Serializable 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。
    Serializable接口也叫标记型接口
        要进行序列化和反序列化的类必须实现Serializable接口,就会给类添加一个标记
        当我们进行序列化和反序列化的时候,就会检测类上是否有这个标记
            有:就可以序列化和反序列化
            没有:就会抛出 NotSerializableException异常
    去市场买肉-->肉上有一个蓝色章(检测合格)-->放心购买-->买回来怎么吃随意


    static关键字:静态关键字
        静态优先于非静态加载到内存中(静态优先于对象进入到内存中)
        被static修饰的成员变量不能被序列化的,序列化的都是对象
        private static int age;
        oos.writeObject(new Person("小美女",18));
        Object o = ois.readObject();
        Person{name='小美女', age=0}

    transient关键字:瞬态关键字
        被transient修饰成员变量,不能被序列化
        private transient int age;
        oos.writeObject(new Person("小美女",18));
        Object o = ois.readObject();
        Person{name='小美女', age=0}

 */

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L; //此UID可任意定义，用于 反序列化时 核对版本
    /* 编译器javac.exe会把Person.java文件编译生成Person.class文件
    * 由于Person类实现了 Serializable接口，则编译器会给Person.class文件，添加一个序列号： serialVersionUID = 1L(任意给的)
    * 对象序列化时，将此序列化UID写入至Person.txt文件中；
    *
    * 当反序列化时，会使用Person.class文件中的序列号和Person.txt中的序列化号进行比较
    * 若序列号相等，则反序列化成功
    * 否则，则抛出 序列化冲突异常Invalid Class Exception
    *
    * 若第一次序列化成功后，(在没有手动指定static final 序列号的情况下！！此时还是靠编译器随机赋予序列号)
    * 我手贱对类作出修改：(修改添加方法、变量等)，——重新编译时，编译器会赋予此Person.class另一个不同的序列号UID
    * ——这与之前写入Person.txt的序列号不一致了！！
    *
    * 然后再次 反序列化此类时，报错：抛出 序列化冲突异常Invalid Class Exception
    *
    * 为了使得：任意修改类，而不会使反序列化报错，故需手动指定一个 static final 序列号serialVersionUID！！
    * 这样 修改前后，编译器不会修改序列号UID；确保修改后 再次进行反序列化时，使用的还是同一个UID！！
    *  */

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

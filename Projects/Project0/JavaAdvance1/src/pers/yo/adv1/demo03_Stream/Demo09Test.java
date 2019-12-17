package pers.yo.adv1.demo03_Stream;

import pers.yo.adv1.demo03_Stream.Student;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

public class Demo09Test {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Student> list = new ArrayList<>();
        list.add( new Student("小明",10) );
        list.add( new Student("小花",1) );
        list.add( new Student("小号",23) );

        FileOutputStream fos = new FileOutputStream( new File("E:\\Data\\Student.txt") );
        ObjectOutputStream oos = new ObjectOutputStream( fos );
        // oos.writeObject( 实例对象 ) 写对象。list集合对象也是一个对象嗷
        oos.writeObject( list ); //序列化集合：集合也是一个对象，直接作为形参传入oos.writeObject(集合对象)即可 实现“序列化集合”

        FileInputStream fis = new FileInputStream( new File("E:\\Data\\Student.txt") );
        ObjectInputStream ois = new ObjectInputStream( fis );
        Object o = ois.readObject(); //读对象readObject()
        ArrayList<Student> list1 = (ArrayList<Student>)o; //向下转型
        for( Student oneStudent:list1 ){
            System.out.println( oneStudent.getName()+"："+oneStudent.getAge() );
        };
        ois.close();
        fis.close();
        oos.close();
        fos.close();

        /* 写对象 oos.writeObject( 某实例对象 )
        * 读对象 Object o = ois.readObject( 空参数 )；
        * 记得要把此实例对象o 向下转型！！这样才能调用此实例对象的具体方法嗷！
        *  */
    }
}

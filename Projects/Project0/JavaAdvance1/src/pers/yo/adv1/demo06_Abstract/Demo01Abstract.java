package pers.yo.adv1.demo06_Abstract;

import pers.yo.adv1.demo06_Abstract.Animal;
import pers.yo.adv1.demo06_Abstract.Cat;

public class Demo01Abstract {
    public static void main(String[] args) {
        //Animal a1 = new Animal(); //错误！不能用 “new 抽象类名” 来直接创建实例对象
        Cat c = new Cat();
        c.eat();
    }
}

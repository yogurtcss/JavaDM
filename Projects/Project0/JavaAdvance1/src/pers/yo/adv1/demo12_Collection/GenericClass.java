package pers.yo.adv1.demo12_Collection;

public class GenericClass<E> { //定义和使用含有泛型的类，在这里泛型为未知的E类型
    private E name;

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}

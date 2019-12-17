package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.Laptop;


public class Demo03Laptop {
    public static void main(String[] args) {
        Laptop lp = new Laptop();
        Laptop.Mouse ms = new Laptop().new Mouse();
        Laptop.Keyboard kb = new Laptop().new Keyboard();

        lp.boot();
        ms.open();
        kb.open();

        ms.click();
        kb.tap();

        ms.close();
        kb.close();
        lp.shutDown();

    }
}

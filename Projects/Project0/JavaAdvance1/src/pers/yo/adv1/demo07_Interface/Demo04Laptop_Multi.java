package pers.yo.adv1.demo07_Interface;


import pers.yo.adv1.demo07_Interface.Laptop2;
import pers.yo.adv1.demo07_Interface.Keyboard;
import pers.yo.adv1.demo07_Interface.Mouse;
import pers.yo.adv1.demo07_Interface.USB;

public class Demo04Laptop_Multi {
    public static void main(String[] args) {
        Laptop2 lp2 = new Laptop2();

        USB usb_ms = new Mouse(); //多态：鼠标向上转型成为USB
        USB usb_kb = new Keyboard(); //多态：键盘向上转型成为USB

        lp2.boot();
        lp2.useDevice( usb_ms );
        lp2.useDevice( usb_kb );
        lp2.shutDown();

    }
}

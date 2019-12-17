package pers.yo.adv1.demo18_ThreadState;

import pers.yo.adv1.demo18_ThreadState.BaoZi;
import pers.yo.adv1.demo18_ThreadState.Guest;

public class Demo02BaoZi {
    public static void main(String[] args) {
        BaoZi bz = new BaoZi();

        new BaoZiPu( bz ).start();
        new Guest( bz ).start();
    }
}

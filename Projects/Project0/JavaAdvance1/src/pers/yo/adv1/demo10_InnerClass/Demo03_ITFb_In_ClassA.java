package pers.yo.adv1.demo10_InnerClass;

import pers.yo.adv1.demo10_InnerClass.Hero;
import pers.yo.adv1.demo10_InnerClass.Weapon;
import pers.yo.adv1.demo10_InnerClass.Skill;
import pers.yo.adv1.demo10_InnerClass.Skill_impl;

public class Demo03_ITFb_In_ClassA {
    public static void main(String[] args) {
        Hero h = new Hero( "北洛", 233 );
        Weapon wp = new Weapon("太岁");
        Skill_impl sk = new Skill_impl(); //技能。实现子类的实例对象

        h.setWp(wp);
        h.setSkill( sk );
        h.attack();
        h.useSkill();

    }
}

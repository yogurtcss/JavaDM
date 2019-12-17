package pers.yo.adv1.demo10_InnerClass;

import pers.yo.adv1.demo10_InnerClass.Skill;
//Skill接口的其中一个实现子类

public class Skill_impl implements Skill {
    public void use(){
        System.out.println( "使用技能 [截风]，biu~ biu~ biu！ " );
    }
}

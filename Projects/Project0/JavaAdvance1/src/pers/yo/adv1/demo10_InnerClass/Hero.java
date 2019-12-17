package pers.yo.adv1.demo10_InnerClass;

import pers.yo.adv1.demo10_InnerClass.Weapon;
import pers.yo.adv1.demo10_InnerClass.Skill; //接口作为成员变量

public class Hero { //游戏中的英雄角色
    private String name; //英雄名
    private int age; //英雄年龄
    private Weapon wp; //另一个类B。英雄所配的武器

    //接口类型。英雄的法术技能，待实现类来 实现此接口，
    private Skill skill; // 然后传入此实现类的实例对象

    public Hero(){
    }

    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Hero(String name, int age, Weapon wp ){
        this.name = name;
        this.age = age;
        this.wp = wp;
    }

    public void attack(){
        //this.wp.getCode() 调用武器实例对象wp的方法，获取武器名
        System.out.println( "年龄为 "+this.age+" 的 "+this.name+" 使用 "+this.wp.getCode()+" 攻击敌方喽！" );
    }
    public void useSkill(){
        System.out.println( "年龄为 "+this.age+" 的 "+this.name+"，开始释放技能");
        this.skill.use(); //调用 此接口Skill实现类的实例对象 的use方法
        System.out.println( "释放技能完成。" );
    };


    public Skill getSkill() { //返回此接口的 实现类
        return skill;
    }

    public void setSkill(Skill skill) { //传入参数为 此接口实现类的实例对象
        this.skill = skill;
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

    public Weapon getWp() {
        return wp;
    }

    public void setWp(Weapon wp) { //注意，这里传入的形参是Weapon的实例对象wp！！
        //所以在main方法中需单独创建一个实例对象wp，然后set进来
        this.wp = wp;
    }
}

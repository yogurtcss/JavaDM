package pers.yo.adv1.demo07_Interface;

import pers.yo.adv1.demo07_Interface.ITF_abs;
import pers.yo.adv1.demo07_Interface.ITF_default;
import pers.yo.adv1.demo07_Interface.ITF_static;
import pers.yo.adv1.demo07_Interface.ITF_const;

/* 多个父接口中的抽象方法若重复，则没关系；
*  多个父接口中的默认方法若重复，则继承的子接口必须对此 重复的默认方法进行覆盖重写，【且带上default关键字嗷！】
*
*  */



public interface ITF_extendsFour extends ITF_abs, ITF_default, ITF_static, ITF_const { //接口是多继承的嗷
    public abstract void method();
}

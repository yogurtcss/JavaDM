package pers.yo.adv1.demo10_InnerClass;

public class Weapon {
    private String code; //武器代号，即武器名name

    public Weapon(){ //无参构造
    };
    public Weapon( String code ){ //有参构造
        this.code = code;
    };

    public void setCode( String code ){
        this.code = code;
    };
    public String getCode(){
        return this.code;
    };


}

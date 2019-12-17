package pers.yo.adv1.demo15_Exception;

public class RegisterException extends Exception {
    public RegisterException(){ //无参构造方法
    }
    public RegisterException( String s ){ //带异常信息的构造方法
        System.out.println( s );
    }
}

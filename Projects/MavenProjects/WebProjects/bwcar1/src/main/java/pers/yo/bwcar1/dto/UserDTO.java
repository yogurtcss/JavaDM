package pers.yo.bwcar1.dto;

//此DTO对象中的属性，要与前端要求的传输数据的属性相一致！
public class UserDTO {
    private String username;
    private String password;
    private String captcha;  //验证码
    private boolean rememberMe; //是否记住我

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}

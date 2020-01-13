package demo02_MyDesign.mybatis.cfg;



import java.util.HashMap;
import java.util.Map;

/* 2020-01-13 11:26:40
* 自定义 mybatis的配置类
*  */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String,Mapper> mappers = new HashMap<>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        //this.mappers = mappers;
        /* 2020-01-13 15:27:24
        * Map.putAll方法：把另一个Map对象【追加】到当前Map集合中
        *  */
        this.mappers.putAll( mappers ); //此处需使用 【追加】存入 的方式嗷
    }
}
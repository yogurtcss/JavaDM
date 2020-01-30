package pers.yo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.yo.domain.Account;

import java.util.List;

@Repository //@Repository 将 DAO 类声明为 Bean
public interface AccountDao {

    @Select( "select * from account" )
    public List<Account> findAll(); //查询所有

    /*  #{} 是一个占位符， mybatis 最后会将这个占位符，替换成？，
    * 最后才进行 预编译SQL语句——prepareStatement 的相应位置的？的替换，
    * 也就是  state.setString(序号，值)，setInt(序号，值）)
    *
    * 通过#{} 可以直接得到实体(实例对象)中 对应属性(字段)所对应的值
    *  */
    @Insert( "insert into account(name,money) values(#{name},#{money})" )
    public void saveAccount( Account account ); //保存
}
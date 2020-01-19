package demo2_annoioc.dao.impl;

import demo2_annoioc.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository( "accountDao2" )
public class AccountDaoImpl2 implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println( "我佛了22222222222222" );
    }
}

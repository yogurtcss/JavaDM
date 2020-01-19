package demo2_annoioc.dao.impl;

import demo2_annoioc.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImpl1 implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println( "保存了帐户！111111111111" );
    }
}

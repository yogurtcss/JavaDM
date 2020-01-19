package demo1_xml_IOC.dao.impl;

import demo1_xml_IOC.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println( "我佛了" );
    }
}

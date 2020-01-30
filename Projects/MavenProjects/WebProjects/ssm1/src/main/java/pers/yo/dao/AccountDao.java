package pers.yo.dao;

import pers.yo.domain.Account;

import java.util.List;

public interface AccountDao {

    public List<Account> findAll(); //查询所有

    public void saveAccount( Account account ); //保存
}

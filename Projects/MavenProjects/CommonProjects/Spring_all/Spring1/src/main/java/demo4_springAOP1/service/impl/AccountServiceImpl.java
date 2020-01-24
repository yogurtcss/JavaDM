package demo4_springAOP1.service.impl;

import demo4_springAOP1.service.AccountService;

public class AccountServiceImpl implements AccountService {
    @Override
    public void saveAccount() {
        System.out.println( "执行了保存喽！" );
    }

    @Override
    public void updateAccount(int i) {
        System.out.println( "执行了更新喽！"+i );
    }

    @Override
    public int deleteAccount() {
        System.out.println( "执行了删除喽！" );
        return 0;
    }
}

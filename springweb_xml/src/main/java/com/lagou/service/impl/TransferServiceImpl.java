package com.lagou.service.impl;

import com.lagou.dao.AccountDao;
import com.lagou.dao.Impl.AccountDaoImpl;
import com.lagou.factory.BeanFactory;
import com.lagou.pojo.Account;
import com.lagou.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public class TransferServiceImpl implements TransferService {


    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, Integer money) throws Exception {

        Account from = accountDao.queryAccountCardNo(fromCardNo);
        Account to = accountDao.queryAccountCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(from);
        accountDao.updateAccountByCardNo(to);
    }
}

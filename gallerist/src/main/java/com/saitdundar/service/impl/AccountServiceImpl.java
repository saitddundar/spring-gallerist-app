package com.saitdundar.service.impl;


import com.saitdundar.dto.DtoAccount;
import com.saitdundar.dto.DtoAccountIU;
import com.saitdundar.model.Account;
import com.saitdundar.repository.AccountRepository;
import com.saitdundar.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {


    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU,account);
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();
        Account savedAccount =accountRepository.save(createAccount(dtoAccountIU));
        BeanUtils.copyProperties(savedAccount,dtoAccount);
        return dtoAccount;
    }
}

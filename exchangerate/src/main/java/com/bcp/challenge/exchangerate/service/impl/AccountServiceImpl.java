package com.bcp.challenge.exchangerate.service.impl;

import com.bcp.challenge.exchangerate.entity.AccountEntity;
import com.bcp.challenge.exchangerate.entity.UserEntity;
import com.bcp.challenge.exchangerate.model.AccountModel;
import com.bcp.challenge.exchangerate.repository.AccountRepository;
import com.bcp.challenge.exchangerate.repository.UserRepository;
import com.bcp.challenge.exchangerate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AccountModel> getAllAccountdByCustomerCode(String username) {
        List<AccountEntity> accountEntities = this.accountRepository.findAccountEntitiesByCustomerCode(username);
        List<AccountModel> accountModels = new ArrayList<>();

        for(AccountEntity account : accountEntities){
            accountModels.add(new AccountModel(account.getId(), account.getCodigo(), account.getEtiqueta(), account.getUser().getUsername(),
                    account.getCurrency().getCodigo(), account.getBalance()));
        }

        return accountModels;
    }
}

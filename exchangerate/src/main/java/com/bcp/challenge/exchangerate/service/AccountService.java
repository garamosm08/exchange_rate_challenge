package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.AccountModel;

import java.util.List;

public interface AccountService {

    List<AccountModel> getAllAccountdByCustomerCode(String username);
}

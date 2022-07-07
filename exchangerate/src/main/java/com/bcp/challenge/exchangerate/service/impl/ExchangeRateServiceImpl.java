package com.bcp.challenge.exchangerate.service.impl;

import com.bcp.challenge.exchangerate.entity.*;
import com.bcp.challenge.exchangerate.model.ExchangeRateRequest;
import com.bcp.challenge.exchangerate.model.ExchangeRateResponse;
import com.bcp.challenge.exchangerate.repository.*;
import com.bcp.challenge.exchangerate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public ExchangeRateResponse doExchangeRate(ExchangeRateRequest request) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Optional<UserEntity> userEntity = Optional.ofNullable(this.userRepository.findByUsername(username));
        if(userEntity.isEmpty()){
            return null;
        }
        UserEntity user = userEntity.get();

        Optional<CurrencyEntity> sourceCurrencyEntity = this.currencyRepository.findById(request.getSourceCurrencyId());
        if(sourceCurrencyEntity.isEmpty()){
            return null;
        }
        CurrencyEntity sourceCurrency = sourceCurrencyEntity.get();

        Optional<CurrencyEntity> targetCurrencyEntity = this.currencyRepository.findById(request.getTargetCurrencyId());
        if(targetCurrencyEntity.isEmpty()){
            return null;
        }
        CurrencyEntity targetCurrency = targetCurrencyEntity.get();

        Optional<AccountEntity> sourceAccountEntity = this.accountRepository.findById(request.getSourceAccountId());
        if(sourceAccountEntity.isEmpty()){
            return null;
        }
        AccountEntity sourceAccount = sourceAccountEntity.get();

        Optional<AccountEntity> targetAccountEntity = this.accountRepository.findById(request.getTargetAccountId());
        if(targetAccountEntity.isEmpty()){
            return null;
        }
        AccountEntity targetAccount = targetAccountEntity.get();

        Optional<ExchangeTypeEntity> exchangeTypeEntity = Optional.ofNullable(this.exchangeRateRepository.findExchangeTypeByForeignCurrency(
                request.getSourceCurrencyId(), request.getTargetCurrencyId()));
        if(exchangeTypeEntity.isEmpty()){
            return null;
        }
        ExchangeTypeEntity exchangeType = exchangeTypeEntity.get();
        Double exchangeRate = (targetCurrency.getCodigo().equalsIgnoreCase("PEN"))
                ? exchangeType.getPurchaseValue() : exchangeType.getSalesValue();
        Double sourceAmount, targetAmount, exchangeRateAmount = request.getAmount() * exchangeRate;

        if(sourceCurrency.getCodigo().equalsIgnoreCase("PEN")){
            sourceAmount = sourceAccount.getBalance() - exchangeRateAmount;
            targetAmount = targetAccount.getBalance() + request.getAmount();
        } else {
            sourceAmount = sourceAccount.getBalance() - request.getAmount();
            targetAmount = targetAccount.getBalance() + exchangeRateAmount;
        }

        if(sourceAmount < 0){
            return null;
        }

        sourceAccount.setBalance(sourceAmount);
        targetAccount.setBalance(targetAmount);

        this.accountRepository.saveAll(List.of(sourceAccount, targetAccount));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setCustomerId(user.getId());
        transaction.setCustomerCode(user.getUsername());
        transaction.setSourceAccountId(sourceAccount.getId());
        transaction.setSourceAccountCode(sourceAccount.getCodigo());
        transaction.setSourceCurrencyId(sourceCurrency.getId());
        transaction.setSourceCurrencyCode(sourceCurrency.getCodigo());
        transaction.setTargetAccountId(targetAccount.getId());
        transaction.setTargetAccountCode(targetAccount.getCodigo());
        transaction.setTargetCurrencyId(targetCurrency.getId());
        transaction.setTargetCurrencyCode(targetCurrency.getCodigo());
        transaction.setSourceAmount(request.getAmount());
        transaction.setTargetAmount(exchangeRateAmount);
        transaction.setExchangeRate(exchangeRate);
        transaction.setRegisterDate(new Date());

        TransactionEntity transactionSaved = this.transactionRepository.save(transaction);

        if(transactionSaved.getId() == null){
            return null;
        }

        return new ExchangeRateResponse(transactionSaved.getId());
    }
}

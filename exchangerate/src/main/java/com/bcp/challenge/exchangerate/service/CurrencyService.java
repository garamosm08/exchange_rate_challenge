package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.CurrencyModel;

import java.util.List;

public interface CurrencyService {

    CurrencyModel createCurrency(CurrencyModel currencyModel);
    List<CurrencyModel> getAllCurrencies();
    CurrencyModel getCurrencyById(Integer id);
}

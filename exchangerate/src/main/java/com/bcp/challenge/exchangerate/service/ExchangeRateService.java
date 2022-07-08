package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.ExchangeRateRequest;
import com.bcp.challenge.exchangerate.model.ExchangeRateResponse;

public interface ExchangeRateService {

    ExchangeRateResponse doExchangeRate(ExchangeRateRequest request);
}

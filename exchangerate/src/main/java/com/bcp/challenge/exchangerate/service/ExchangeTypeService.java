package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.ExchangeTypeRequest;
import com.bcp.challenge.exchangerate.model.ExchangeTypeResponse;

import java.util.List;

public interface ExchangeTypeService {

    List<ExchangeTypeResponse> getAllExchangeTypes();
    ExchangeTypeResponse getExchangeTypeById(Long id);
    ExchangeTypeResponse createExchangeType(ExchangeTypeRequest request);
    void updateExchangeType(Long id, ExchangeTypeRequest request);
}

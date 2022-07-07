package com.bcp.challenge.exchangerate.service.impl;

import com.bcp.challenge.exchangerate.entity.CurrencyEntity;
import com.bcp.challenge.exchangerate.entity.ExchangeTypeEntity;
import com.bcp.challenge.exchangerate.entity.RoleEntity;
import com.bcp.challenge.exchangerate.model.ExchangeTypeRequest;
import com.bcp.challenge.exchangerate.model.ExchangeTypeResponse;
import com.bcp.challenge.exchangerate.repository.CurrencyRepository;
import com.bcp.challenge.exchangerate.repository.ExchangeRateRepository;
import com.bcp.challenge.exchangerate.service.CurrencyService;
import com.bcp.challenge.exchangerate.service.ExchangeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeTypeServiceImpl implements ExchangeTypeService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<ExchangeTypeResponse> getAllExchangeTypes() {
        List<ExchangeTypeEntity> entityList = this.exchangeRateRepository.findAll();
        List<ExchangeTypeResponse> typeResponses = new ArrayList<>();
        for(ExchangeTypeEntity entity : entityList){
            typeResponses.add(new ExchangeTypeResponse(entity.getId(), entity.getLocalCurrency().getCodigo(),
                    entity.getLocalCurrency().getEtiqueta(), entity.getForeignCurrency().getCodigo(),
                    entity.getForeignCurrency().getEtiqueta(), entity.getPurchaseValue(), entity.getSalesValue()));
        }

        return typeResponses;
    }

    @Override
    public ExchangeTypeResponse getExchangeTypeById(Long id) {
        Optional<ExchangeTypeEntity> entityOptional = this.exchangeRateRepository.findById(id);
        if(entityOptional.isEmpty()){
            return null;
        }

        ExchangeTypeEntity entity = entityOptional.get();

        return new ExchangeTypeResponse(entity.getId(), entity.getLocalCurrency().getCodigo(), entity.getLocalCurrency().getEtiqueta(),
                entity.getForeignCurrency().getCodigo(), entity.getForeignCurrency().getEtiqueta(),
                entity.getPurchaseValue(), entity.getSalesValue());
    }

    @Override
    public ExchangeTypeResponse createExchangeType(ExchangeTypeRequest request) {
        Optional<CurrencyEntity> localCurrency = this.currencyRepository.findById(request.getLocalCurrencyId());
        if(localCurrency.isEmpty()){
            return null;
        }
        Optional<CurrencyEntity> foreignCurrency = this.currencyRepository.findById(request.getForeignCurrencyId());
        if(foreignCurrency.isEmpty()){
            return null;
        }

        ExchangeTypeEntity exchangeTypeEntity = new ExchangeTypeEntity();
        exchangeTypeEntity.setLocalCurrency(localCurrency.get());
        exchangeTypeEntity.setForeignCurrency(foreignCurrency.get());
        exchangeTypeEntity.setPurchaseValue(request.getPurchaseValue());
        exchangeTypeEntity.setSalesValue(request.getSalesValue());
        exchangeTypeEntity.setCreationDate(new Date());

        ExchangeTypeEntity exchangeTypeSaved = this.exchangeRateRepository.save(exchangeTypeEntity);

        return new ExchangeTypeResponse(exchangeTypeSaved.getId(), exchangeTypeSaved.getLocalCurrency().getCodigo(),
                exchangeTypeSaved.getLocalCurrency().getEtiqueta(), exchangeTypeSaved.getForeignCurrency().getCodigo(),
                exchangeTypeSaved.getForeignCurrency().getEtiqueta(), exchangeTypeSaved.getPurchaseValue(),
                exchangeTypeSaved.getSalesValue());
    }

    @Override
    public void updateExchangeType(Long id, ExchangeTypeRequest request) {
        Optional<ExchangeTypeEntity> exchangeTypeEntity = this.exchangeRateRepository.findById(id);
        if(exchangeTypeEntity.isEmpty()){
            return;
        }

        ExchangeTypeEntity exchangeType = exchangeTypeEntity.get();
        exchangeType.setPurchaseValue(request.getPurchaseValue());
        exchangeType.setSalesValue(request.getSalesValue());
        exchangeType.setUpdateDate(new Date());

        this.exchangeRateRepository.save(exchangeType);
    }
}

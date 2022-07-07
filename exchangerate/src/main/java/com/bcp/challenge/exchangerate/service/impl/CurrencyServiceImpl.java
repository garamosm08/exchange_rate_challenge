package com.bcp.challenge.exchangerate.service.impl;

import com.bcp.challenge.exchangerate.entity.CurrencyEntity;
import com.bcp.challenge.exchangerate.model.CurrencyModel;
import com.bcp.challenge.exchangerate.repository.CurrencyRepository;
import com.bcp.challenge.exchangerate.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    @Override
    @Transactional
    public CurrencyModel createCurrency(CurrencyModel currencyModel) {
        CurrencyEntity entity = new CurrencyEntity();
        entity.setCodigo(currencyModel.getCodigo());
        entity.setEtiqueta(currencyModel.getEtiqueta());
        entity.setFecha(new Date());

        CurrencyEntity entityDb = this.repository.save(entity);

        return new CurrencyModel(entityDb.getId(), entityDb.getCodigo(), entityDb.getEtiqueta());
    }

    @Override
    public List<CurrencyModel> getAllCurrencies() {
        List<CurrencyEntity> currencyModelList = this.repository.findAll();
        List<CurrencyModel> currencyModels = new ArrayList<>();

        for(CurrencyEntity currency : currencyModelList){
            currencyModels.add(new CurrencyModel(currency.getId(), currency.getCodigo(), currency.getEtiqueta()));
        }

        return currencyModels;
    }

    @Override
    public CurrencyModel getCurrencyById(Integer id) {
        Optional<CurrencyEntity> currencyEntity = this.repository.findById(id);
        if(currencyEntity.isEmpty()){
            return null;
        }

        CurrencyEntity currency = currencyEntity.get();

        return new CurrencyModel(currency.getId(), currency.getCodigo(), currency.getEtiqueta());
    }
}

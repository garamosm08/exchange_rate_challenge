package com.bcp.challenge.exchangerate.controller;

import com.bcp.challenge.exchangerate.model.CurrencyModel;
import com.bcp.challenge.exchangerate.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<CurrencyModel> createCurrency(@RequestBody CurrencyModel currencyModel){
        CurrencyModel currency = this.service.createCurrency(currencyModel);

        return new ResponseEntity<>(currency, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("")
    public ResponseEntity<List<CurrencyModel>> getAllCurrencies(){
        List<CurrencyModel> currencyModels = this.service.getAllCurrencies();
        if(currencyModels.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(currencyModels, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyModel> getCurrencyById(@PathVariable Integer id){
        CurrencyModel currencyModel = this.service.getCurrencyById(id);
        if(currencyModel == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(currencyModel, HttpStatus.OK);
    }
}

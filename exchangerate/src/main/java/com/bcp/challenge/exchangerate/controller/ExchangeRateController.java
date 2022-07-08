package com.bcp.challenge.exchangerate.controller;

import com.bcp.challenge.exchangerate.model.ExchangeRateRequest;
import com.bcp.challenge.exchangerate.model.ExchangeRateResponse;
import com.bcp.challenge.exchangerate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchangerates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("")
    public ResponseEntity<ExchangeRateResponse> doExchangeRate(@RequestBody ExchangeRateRequest request){
        ExchangeRateResponse exchangeRateResponse = this.service.doExchangeRate(request);

        return new ResponseEntity<>(exchangeRateResponse, HttpStatus.CREATED);
    }
}

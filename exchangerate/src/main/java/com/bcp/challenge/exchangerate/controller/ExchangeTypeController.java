package com.bcp.challenge.exchangerate.controller;

import com.bcp.challenge.exchangerate.model.ExchangeTypeRequest;
import com.bcp.challenge.exchangerate.model.ExchangeTypeResponse;
import com.bcp.challenge.exchangerate.service.ExchangeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchangetypes")
public class ExchangeTypeController {

    @Autowired
    private ExchangeTypeService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("")
    public ResponseEntity<List<ExchangeTypeResponse>> getAllExchangeTypes(){
        List<ExchangeTypeResponse> exchangeTypeResponseList = this.service.getAllExchangeTypes();
        if(exchangeTypeResponseList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(exchangeTypeResponseList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeTypeResponse> getExchangeTypeById(@PathVariable Long id){
        ExchangeTypeResponse exchangeTypeResponse = this.service.getExchangeTypeById(id);
        if(exchangeTypeResponse == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(exchangeTypeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<ExchangeTypeResponse> createExchangeType(@RequestBody ExchangeTypeRequest request) {
        ExchangeTypeResponse exchangeTypeResponse = this.service.createExchangeType(request);

        return new ResponseEntity<>(exchangeTypeResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateExchangeType(@PathVariable Long id, @RequestBody ExchangeTypeRequest request){
        this.service.updateExchangeType(id, request);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

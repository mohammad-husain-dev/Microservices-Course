package com.dilshad.microservices.currency_exchange_service.controller;

import com.dilshad.microservices.currency_exchange_service.beans.CurrencyExchange;
import com.dilshad.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import com.dilshad.microservices.currency_exchange_service.repository.exceptions.CurrencyExchangeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrecnyExchangeController {

    @Autowired
    private Environment env;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retreiveExchangeValue(@PathVariable String from, @PathVariable String to) {

        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new  CurrencyExchangeNotFoundException("Currency exchange not found "+from+"-->"+to);
        }
        String port=env.getProperty("local.server.port");
        currencyExchange.setEnv(port);
        return currencyExchange;
    }
}

package com.dilshad.microservices.currency_conversion_service.controller;

import com.dilshad.microservices.currency_conversion_service.beans.CurrencyConversion;
import com.dilshad.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> restTemplate = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                                                                         CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = restTemplate.getBody();
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                                        currencyConversion.getConversionMultiple(),
                                        quantity.multiply(currencyConversion.getConversionMultiple()),
                                        currencyConversion.getEnvironment()+" rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retreiveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment()+" feign");
    }
}

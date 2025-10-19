package com.dilshad.microservices.currency_conversion_service.proxy;

import com.dilshad.microservices.currency_conversion_service.beans.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retreiveExchangeValue(@PathVariable String from,
                                                    @PathVariable String to);
}

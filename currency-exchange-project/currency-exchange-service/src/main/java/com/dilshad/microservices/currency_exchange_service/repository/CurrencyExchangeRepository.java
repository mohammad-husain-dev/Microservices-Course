package com.dilshad.microservices.currency_exchange_service.repository;

import com.dilshad.microservices.currency_exchange_service.beans.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}

package com.dilshad.microservices.currency_exchange_service.repository.exceptions;

public class CurrencyExchangeNotFoundException extends RuntimeException {
    private String message;
    public CurrencyExchangeNotFoundException(String message) {
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}

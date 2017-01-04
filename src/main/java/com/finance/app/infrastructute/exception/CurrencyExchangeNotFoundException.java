package com.finance.app.infrastructute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such CurrencyExchange")
public class CurrencyExchangeNotFoundException extends RuntimeException {

    private String value;

    public CurrencyExchangeNotFoundException(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

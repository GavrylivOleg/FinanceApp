package com.finance.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrencyExchange {

    private boolean success;

    private String terms;

    private String privacy;

    private long timestamp;

    private String source;

    private Quotes quotes;

}

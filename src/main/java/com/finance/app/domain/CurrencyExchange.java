package com.finance.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {

    private boolean success;

    private String terms;

    private String privacy;

    private long timestamp;

    private String source;

    private Quotes quotes;

}

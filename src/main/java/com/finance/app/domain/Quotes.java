package com.finance.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {

    private int USDUSD;

    private double USDAUD;

    private double USDCAD;

    private double USDPLN;

    private double USDMXN;

}

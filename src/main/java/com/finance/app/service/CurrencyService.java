package com.finance.app.service;

import com.finance.app.domain.CurrencyExchange;
import java.util.Optional;

public interface CurrencyService {

    Optional<CurrencyExchange> getCurrency();
}

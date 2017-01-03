package com.finance.app.service.implementation;

import com.finance.app.domain.CurrencyExchange;
import com.finance.app.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Override
    public Optional<CurrencyExchange> getCurrency() {
        LOGGER.info("CurrencyServiceImpl.getCurrency()");
        RestTemplate restTemplate = new RestTemplate();
        CurrencyExchange currencyExchange = restTemplate.getForObject("http://apilayer.net/api/live?access_key=b6a21385f0b87cf03a98510d66e9ba2d&currencies=USD,AUD,CAD,PLN,MXN&format=1",CurrencyExchange.class);
        LOGGER.info("CurrencyServiceImpl.getCurrency() finished");
        return Optional.of(currencyExchange);
    }
}





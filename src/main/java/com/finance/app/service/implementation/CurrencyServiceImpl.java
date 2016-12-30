package com.finance.app.service.implementation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.finance.app.domain.CurrencyExchange;
import com.finance.app.service.CurrencyService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    private CloseableHttpClient httpClient;

    @Override
    public Optional<CurrencyExchange> getCurrency() {
        LOGGER.info("CurrencyServiceImpl.getCurrency()");
        HttpGet get = new HttpGet("http://apilayer.net/api/live?access_key=b6a21385f0b87cf03a98510d66e9ba2d&currencies=USD,AUD,CAD,PLN,MXN&format=1");
        CurrencyExchange currencyExchange = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try (CloseableHttpResponse e = httpClient.execute(get)) {
            HttpEntity entity = e.getEntity();
            String result = EntityUtils.toString(entity);
            currencyExchange = objectMapper.readValue(result, CurrencyExchange.class);
        } catch (IOException e) {
            LOGGER.error("can't load currencyExchange");
            e.printStackTrace();
        }

        LOGGER.info("CurrencyServiceImpl.getCurrency() finished");
        return Optional.of(currencyExchange);
    }
}





package com.finance.app.controller;

import com.finance.app.domain.CurrencyExchange;
import com.finance.app.infrastructute.exception.CurrencyExchangeNotFoundException;
import com.finance.app.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currencyExchange")
    public String getCurrencyExchange(Model model)  {
        LOGGER.info("CurrencyController.getCurrencyExchange()");
        CurrencyExchange currencyExchange = currencyService.getCurrency().orElseThrow(() -> new CurrencyExchangeNotFoundException("No such CurrencyExchange"));
        model.addAttribute("currencyExchange", currencyExchange);
        LOGGER.info("CurrencyController.getCurrencyExchange() finished");
        return "currencyExchange";
    }
}

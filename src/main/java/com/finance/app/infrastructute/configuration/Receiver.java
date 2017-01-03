package com.finance.app.infrastructute.configuration;

import com.finance.app.controller.UserController;
import com.finance.app.domain.MessageToEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(MessageToEmail messageToEmail) {
        LOGGER.info("Received <" + messageToEmail + ">");
    }
}

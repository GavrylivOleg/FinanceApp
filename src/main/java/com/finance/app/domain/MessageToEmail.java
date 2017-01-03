package com.finance.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageToEmail {

    private String to;
    private String body;


    @Override
    public String toString() {
        return String.format("MessageToEmail{to=%s, body=%s}", getTo(), getBody());
    }

}

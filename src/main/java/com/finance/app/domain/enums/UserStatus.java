package com.finance.app.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum UserStatus {

    ACTIVE(200), UNDER_VERIFICATION(206);

    @Getter
    private int code;

}

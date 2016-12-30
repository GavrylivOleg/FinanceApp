package com.finance.app.domain.enums;

import lombok.Getter;

public enum UserStatus {

    ACTIVE(200), SUSPENDED(403), UNDER_VERIFICATION(206);

    @Getter
    private int code;

    UserStatus(int code) {
        this.code = code;
    }

}

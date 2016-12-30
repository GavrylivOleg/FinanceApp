package com.finance.app.infrastructute.security;

import com.finance.app.constant.FinanceConstants;
import com.finance.app.domain.enums.UserStatus;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FinanceAuthHandler implements AuthenticationSuccessHandler {

    private final static String ACTIVE = "ACTIVE";
    private final static String UNDER_VERIFICATION = "UNDER_VERIFICATION";
    private final static String SUSPENDED = "SUSPENDED";

    @Getter
    private AuthenticationSuccessHandler defaultHandler;

    public FinanceAuthHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {

        FinanceUserDetailService.FinanceUserDetails principal = (FinanceUserDetailService.FinanceUserDetails) authentication.getPrincipal();

        httpServletResponse.setStatus(returnCode(principal));
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(FinanceConstants.USERID, principal.getId());
    }

    private int returnCode(FinanceUserDetailService.FinanceUserDetails details) {

        int code = 0;

        switch (details.getUserStatus().toUpperCase()) {

            case ACTIVE:
                code = UserStatus.ACTIVE.getCode();
                break;

            case UNDER_VERIFICATION:
                code = UserStatus.UNDER_VERIFICATION.getCode();
                break;

            case SUSPENDED:
                code = UserStatus.SUSPENDED.getCode();
                break;
        }

        return code;
    }
}


package com.finance.app.infrastructute.security;

import com.finance.app.constant.FinanceConstants;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.finance.app.domain.enums.UserStatus.ACTIVE;
import static com.finance.app.domain.enums.UserStatus.UNDER_VERIFICATION;


public class FinanceAuthHandler implements AuthenticationSuccessHandler {

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
        session.setAttribute(FinanceConstants.USER_ID, principal.getId());
    }

    private int returnCode(FinanceUserDetailService.FinanceUserDetails details) {

        int code = 0;

        switch (details.getUserStatus()) {

            case ACTIVE:
                code = ACTIVE.getCode();
                break;

            case UNDER_VERIFICATION:
                code = UNDER_VERIFICATION.getCode();
                break;
        }

        return code;
    }
}


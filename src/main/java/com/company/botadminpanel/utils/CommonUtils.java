package com.company.botadminpanel.utils;

import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Admin;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CommonUtils {

    public static Admin getCurrentUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
            throw RestException.restThrow("OKa yopiq yul", HttpStatus.UNAUTHORIZED);

        return (Admin) authentication.getPrincipal();
    }
}

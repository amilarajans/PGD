package com.origins.pgd.util;

import com.origins.pgd.domain.User;
import com.origins.pgd.web.rest.exception.NotPermittedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public class SecurityUtils {
    public static synchronized User getCurrentLogin() {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails springSecurityUser = (UserDetails) securityContext.getAuthentication().getPrincipal();
            return new User(springSecurityUser.getUsername());
        } catch (Exception e) {
            throw new NotPermittedException("Request did not contain valid authorization");
        }
    }
}

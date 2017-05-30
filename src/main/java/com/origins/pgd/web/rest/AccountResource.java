package com.origins.pgd.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.origins.pgd.domain.User;
import com.origins.pgd.repository.UserRepository;
import com.origins.pgd.security.SecurityUtils;
import com.origins.pgd.web.rest.exception.NotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by Amila-Kumara on 3/12/2016.
 */
@RestController
public class AccountResource {
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AccountResource(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Timed
    @RequestMapping(value = {"/rest/authenticate"}, method = {RequestMethod.GET}, produces = {"application/json"})
    public ObjectNode isAuthenticated(HttpServletRequest request) {
        String user = request.getRemoteUser();
        if (user == null) {
            throw new NotPermittedException("Request did not contain valid authorization");
        }
        ObjectNode result = this.objectMapper.createObjectNode();
        result.put("login", user);
        return result;
    }

    @RequestMapping(value = {"/rest/account"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public User getAccount(HttpServletResponse response) {
        User user = this.userRepository.findOne(SecurityUtils.getCurrentLogin());
        if (user == null) {
            response.setStatus(500);
        }
        return user;
    }

    @RequestMapping(value = {"/rest/isAdmin"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public ObjectNode getUserAuthority(Authentication principal) {
        ObjectNode result = this.objectMapper.createObjectNode();
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        result.put("admin", isAdmin);
        return result;
    }
}
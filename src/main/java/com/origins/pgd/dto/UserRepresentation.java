package com.origins.pgd.dto;

import com.origins.pgd.domain.Authority;
import com.origins.pgd.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amila-Kumara on 3/12/2016.
 */
@Getter
@Setter
public class UserRepresentation {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> authorities = new ArrayList();

    public UserRepresentation() {
    }

    public UserRepresentation(User user) {
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        if (user.getAuthorities() != null) {
            for (Authority authority : user.getAuthorities()) {
                this.authorities.add(authority.getName());
            }
        }
    }
}
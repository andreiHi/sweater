package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.09.2018.
 * @version $Id$.
 * @since 0.1.
 */
public enum  Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}

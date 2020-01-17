package com.cove.user.model.entities;

import lombok.Data;
import lombok.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
public class TenantAuthenticationToken extends AbstractAuthenticationToken implements Serializable {
    @NonNull
    private Serializable tenantIdentifier;

    public TenantAuthenticationToken(Serializable tenantIdentifier, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        setTenantIdentifier(tenantIdentifier);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
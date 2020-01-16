package com.cove.user.services;

import com.cove.user.model.entities.TenantAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class TenantService {
    @PersistenceContext
    public EntityManager entityManager;

    public Serializable getCurrentTenantIdentifer() {
        return ((TenantAuthenticationToken)
                (SecurityContextHolder
                        .getContext()
                        .getAuthentication()))
                .getTenantIdentifier();
    }
}

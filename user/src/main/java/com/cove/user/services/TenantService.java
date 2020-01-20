package com.cove.user.services;

import com.cove.user.model.entities.TenantAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Service
public abstract class TenantService {
    @PersistenceContext
    EntityManager entityManager;

//    Serializable getCurrentTenantIdentifer() {
//        return ((TenantAuthenticationToken)
//                (SecurityContextHolder
//                        .getContext()
//                        .getAuthentication()))
//                .getTenantIdentifier();
//    }
}

package com.cove.user.services;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

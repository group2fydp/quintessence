package com.cove.user.config;

import com.cove.user.services.TenantService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;

import static com.cove.user.model.entities.TenantEntity.TENANT_FILTER_ARGUMENT_NAME;
import static com.cove.user.model.entities.TenantEntity.TENANT_FILTER_NAME;

@Aspect
public class TenantServiceAspect {
    @Before("execution(* com.cove.user.services.TenantService+.*(..)) " +         /*I*/
            "&& @annotation(com.cove.user.annotatations.ReadsTenantData) " + /*II*/
            "&& target(TenantService)")                                                /*III*/
    public void before(JoinPoint joinPoint, TenantService tenantService){
        tenantService.entityManager                                                    /*IV*/
                .unwrap(Session.class)                                                 /*V*/
                .enableFilter(TENANT_FILTER_NAME)                                      /*VI*/
                .setParameter(TENANT_FILTER_ARGUMENT_NAME,
                        tenantService.getCurrentTenantIdentifer());              /*VII*/
    }
}

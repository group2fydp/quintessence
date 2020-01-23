package com.cove.user.services;

import com.cove.user.config.TenantContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.cove.user.model.entities.TenantEntity.*;

@Aspect
@Component
public class TenantServiceAspect {

    @Autowired
    private StudentService studentService;

    @Before("execution(* com.cove.user.services.TenantService+.*(..)) " +
            "&& args(tenantService) " +
            "&& target(TenantService)")
    public void before(JoinPoint joinPoint, TenantService tenantService) throws Throwable{
        System.out.println("Testing pointcut");
        Filter filter = tenantService.entityManager.unwrap(Session.class).enableFilter(TENANT_FILTER_NAME);
        filter.setParameter(TENANT_ID_PROPERTY_NAME, TenantContext.getCurrentTenant());
        filter.validate();
    }
}

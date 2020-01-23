package com.cove.user.config;

import com.cove.user.model.entities.TenantEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class TenantInterceptor{
    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    public EmptyInterceptor hibernateInterceptor() {
        return new EmptyInterceptor() {

            @Override
            public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantEntity) {
                    ((TenantEntity) entity).setTenantId(TenantContext.getCurrentTenant());
                }
            }


            @Override
            public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantEntity) {
                    ((TenantEntity) entity).setTenantId(TenantContext.getCurrentTenant());
                }
                return false;
            }

            @Override
            public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantEntity) {
                    ((TenantEntity) entity).setTenantId(TenantContext.getCurrentTenant());
                }
                return false;
            }
        };
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory, DataSource dataSource, JpaProperties properties) {
        Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
        jpaPropertiesMap.put("hibernate.ejb.interceptor", hibernateInterceptor());
        return factory.dataSource(dataSource).packages("com.cove.user").properties(jpaPropertiesMap).build();
    }
}

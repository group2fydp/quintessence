package com.cove.user.config;

import com.cove.user.model.entities.TenantAuthenticationToken;
import com.cove.user.model.entities.TenantEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

public class TenantInterceptor extends EmptyInterceptor {
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return addTenantIdIfObjectIsTenantEntity(entity, state, propertyNames);
    }
    /*II*/
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return addTenantIdIfObjectIsTenantEntity(entity, currentState, propertyNames);
    }
    /*III*/
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        addTenantIdIfObjectIsTenantEntity(entity, state, propertyNames);
    }
    /*IV*/
    private boolean addTenantIdIfObjectIsTenantEntity(Object entity, Object[] state, String[] propertyName){
        if(entity instanceof TenantEntity){                                    /*V*/
            for (int index = 0; index < propertyName.length; index++) {        /*VI*/
                if(propertyName[index].equals("tenantId")){       /*VII*/
                    state[index] = ((TenantAuthenticationToken) (SecurityContextHolder.getContext().getAuthentication())).getTenantIdentifier();// We have to get the request's tenantId    /*VIII*/
                    return true;   // from somewhere, we'll come back to this.
                }
            }
            throw new ClassCastException();                                    /*IX*/
        }
        return false;
    }
}

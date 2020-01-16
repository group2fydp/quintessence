package com.cove.user.model.entities;

import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass()
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@FilterDef(
        name = TenantEntity.TENANT_FILTER_NAME,
        parameters = @ParamDef(name = TenantEntity.TENANT_FILTER_ARGUMENT_NAME, type = "long"),
        defaultCondition = TenantEntity.TENANT_ID_PROPERTY_NAME + "= :" + TenantEntity.TENANT_FILTER_ARGUMENT_NAME)
@Filter(name = TenantEntity.TENANT_FILTER_NAME)
public class TenantEntity {
    public static final String TENANT_FILTER_NAME = "tenantFilter";
    public static final String TENANT_ID_PROPERTY_NAME = "tenantId";
    public static final String TENANT_FILTER_ARGUMENT_NAME = TENANT_ID_PROPERTY_NAME;
    Long tenantId;
}

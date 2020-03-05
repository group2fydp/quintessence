package com.cove.user.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass()
@FilterDef(
        name = TenantEntity.TENANT_FILTER_NAME,
        parameters = @ParamDef(name = TenantEntity.TENANT_FILTER_ARGUMENT_NAME, type = "long"),
        defaultCondition = TenantEntity.TENANT_ID_PROPERTY_NAME + "= :" + TenantEntity.TENANT_FILTER_ARGUMENT_NAME)
@Filter(name = TenantEntity.TENANT_FILTER_NAME)
public class TenantEntity {
    public static final String TENANT_FILTER_NAME = "tenantFilter";
    public static final String TENANT_ID_PROPERTY_NAME = "tenantId";
    public static final String TENANT_FILTER_ARGUMENT_NAME = TENANT_ID_PROPERTY_NAME;

    @Column(name = "tenant_id")
    Long tenantId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}

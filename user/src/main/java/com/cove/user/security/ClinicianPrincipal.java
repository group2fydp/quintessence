package com.cove.user.security;

import com.cove.user.model.entities.Clinician;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ClinicianPrincipal implements UserDetails {
    private final Clinician clinician;
    public ClinicianPrincipal (Clinician clinician) {
        this.clinician = clinician;
    }

    @Override
    public String getUsername(){
        return clinician.getUsername();
    }

    @Override
    public String getPassword(){
        return clinician.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("Clinician"));
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

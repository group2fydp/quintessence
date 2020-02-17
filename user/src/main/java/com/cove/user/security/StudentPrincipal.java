package com.cove.user.security;

import com.cove.user.model.entities.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StudentPrincipal implements UserDetails {
    private final Student student;

    public StudentPrincipal(Student student) {
        this.student = student;
    }

    @Override
    public String getUsername(){
        return student.getUsername();
    }

    @Override
    public String getPassword(){
        return student.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("Student"));
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

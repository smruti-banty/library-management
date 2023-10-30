package com.lms.backend.model;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.backend.constants.UserRole;
import com.lms.backend.constants.UserStatus;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User implements UserDetails {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String referenceNumber;
    @Field(targetType = FieldType.STRING)
    private UserRole userRole;
    @Field(targetType = FieldType.STRING)
    private UserStatus userStatus;
    private String profilePic;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return referenceNumber;
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
        return userStatus == UserStatus.ACTIVE;
    }
}
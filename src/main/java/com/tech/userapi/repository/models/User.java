package com.tech.userapi.repository.models;

import com.tech.userapi.repository.base.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends ModelBase implements UserDetails {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Phone> phones;

    @Override
    public boolean isEnabled() {
        return getIsActive();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isEnabled();
    }
}

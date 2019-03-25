package com.pactomais.desafio.config.security;

import com.pactomais.desafio.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserSS implements UserDetails { // Usuário especifico do spring security

    private Integer id;
    private String email;
    private String senha;
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    private Integer perfil;

    public UserSS() {

    }

    public UserSS(Integer id, String email, String senha, Integer perfil) {
        Set<Perfil> perfils = new HashSet<>();
        perfils.add(Perfil.toEnum(perfil));

        this.perfil = perfil;
        this.id = id;
        this.email = email;
        this.senha = senha;
        authorities.add(new SimpleGrantedAuthority(Perfil.toEnum(perfil).getDescricao()));
    }

    public Integer getId() {
        return id;
    }

    public Integer getPerfil() { return perfil; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

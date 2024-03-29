package com.pactomais.desafio.services;

import com.pactomais.desafio.config.security.UserSS;
import com.pactomais.desafio.entitys.Usuario;
import com.pactomais.desafio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { // Implmentando a busca por email e criando o UserDetails

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getTipoUsuario());
    }
}

package com.pactomais.desafio.config;

import com.pactomais.desafio.entitys.Usuario;
import com.pactomais.desafio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void instantiateTesteDatabase() {
        Usuario usuario1 = new Usuario(null, "PactoMais", "pactomais@gmail.com", bCryptPasswordEncoder.encode("123456789"), 1);
        Usuario usuario2 = new Usuario(null, "Usuario02", "usuario02@gmail.com", bCryptPasswordEncoder.encode("123456789"), 2);
        this.usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
    }
}

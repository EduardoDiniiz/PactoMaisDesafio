package com.pactomais.desafio.services;

import com.pactomais.desafio.config.security.JWTUtil;
import com.pactomais.desafio.config.security.UserSS;
import com.pactomais.desafio.dto.UsuarioDTO;
import com.pactomais.desafio.entitys.JwtRequest;
import com.pactomais.desafio.entitys.JwtResponse;
import com.pactomais.desafio.entitys.Usuario;
import com.pactomais.desafio.exceptions.ObjectNotFoundException;
import com.pactomais.desafio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsuarioRepository repository;

    public Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), bCryptPasswordEncoder.encode(usuarioDTO.getSenha()), usuarioDTO.getTipoUsuario());
    }

    public Usuario insert(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " + Usuario.class.getName()));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public ResponseEntity<JwtResponse>login(JwtRequest jwtRequest){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = "Bearer " + jwtUtil.generateToken(jwtRequest.getEmail());
        UserSS userSS = (UserSS) auth.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userSS.getUsername(), userSS.getPerfil()));
    }
}

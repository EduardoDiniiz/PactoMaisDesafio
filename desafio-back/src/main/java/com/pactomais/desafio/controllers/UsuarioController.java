package com.pactomais.desafio.controllers;

import com.pactomais.desafio.dto.UsuarioDTO;
import com.pactomais.desafio.entitys.JwtRequest;
import com.pactomais.desafio.entitys.JwtResponse;
import com.pactomais.desafio.entitys.Usuario;
import com.pactomais.desafio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.fromDTO(usuarioDTO);
        usuario = usuarioService.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario usuarioReturn = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuarioReturn);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        Usuario usuarioReturn = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(usuarioReturn);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
        return usuarioService.login(jwtRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> listDTO = usuarios.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}

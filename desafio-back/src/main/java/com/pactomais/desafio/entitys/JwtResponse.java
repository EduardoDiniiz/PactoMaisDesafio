package com.pactomais.desafio.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pactomais.desafio.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String email;
    @JsonIgnore
    private Integer tipo;

    public JwtResponse(){

    }

    public JwtResponse(String token, String email, Integer tipo) {
        this.token = token;
        this.email = email;
        this.tipo = tipo;
    }

    public Perfil getPerfil() {
        return Perfil.toEnum(tipo);
    }
}

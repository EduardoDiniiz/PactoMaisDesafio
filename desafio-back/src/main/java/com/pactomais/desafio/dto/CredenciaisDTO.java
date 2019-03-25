package com.pactomais.desafio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CredenciaisDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String senha;

    public CredenciaisDTO() {

    }

    public CredenciaisDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}

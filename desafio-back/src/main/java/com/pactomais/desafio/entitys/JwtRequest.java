package com.pactomais.desafio.entitys;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    private String email;
    private String senha;
}

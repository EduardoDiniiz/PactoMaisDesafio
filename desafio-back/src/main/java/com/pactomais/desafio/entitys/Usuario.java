package com.pactomais.desafio.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String senha;
    private Integer tipoUsuario;

    public Usuario() {

    }

    public Usuario(Integer id, String nome, String email, String senha, Integer tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
}

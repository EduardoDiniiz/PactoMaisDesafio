package com.pactomais.desafio.enums;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"), // Exigencia do spring security
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (Perfil perfil: Perfil.values()){
            if(cod.equals(perfil.getCod())){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
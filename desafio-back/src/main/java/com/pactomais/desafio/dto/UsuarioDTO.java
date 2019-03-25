package com.pactomais.desafio.dto;

import com.pactomais.desafio.entitys.Usuario;
import com.pactomais.desafio.enums.Perfil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho do nome deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 8, max = 120, message = "O tamanho minimo da senha é 8 caracteres")
    private String senha;
    @NotNull
    private Integer tipoUsuario;


    public UsuarioDTO() {

    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    public Perfil getTipo() {
        return Perfil.toEnum(tipoUsuario);
    }
}

package com.danielarrais.algafood.api.dto.input.usuario;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoIdInput;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class UsuarioInput {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotEmpty
    private List<@Valid GrupoIdInput> grupos;
}
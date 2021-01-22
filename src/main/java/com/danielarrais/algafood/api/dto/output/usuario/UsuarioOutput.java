package com.danielarrais.algafood.api.dto.output.usuario;

import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UsuarioOutput {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private OffsetDateTime dataCadastro;
    private List<GrupoOutput> grupos;
}
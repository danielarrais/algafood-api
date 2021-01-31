package com.danielarrais.algafood.api.dto.output.usuario;

import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import lombok.Data;

import java.util.List;
@Data
public class UsuarioOutput {
    private Long id;
    private String nome;
    private String email;
    private List<GrupoOutput> grupos;
}
package com.danielarrais.algafood.api.dto.output.cidade;

import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import lombok.Data;


@Data
public class CidadeOutput {
    private Long id;
    private String nome;
    private EstadoOutput estado;
}
package com.danielarrais.algafood.api.dto.input.cidade;

import com.danielarrais.algafood.api.dto.input.estado.EstadoIdInput;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CidadeInput {
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
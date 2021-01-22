package com.danielarrais.algafood.api.dto.input.permissao;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PermissaoIdInput {
    @NotNull
    private Long id;
}
package com.danielarrais.algafood.api.dto.input.permissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoIdInput {
    @NotNull
    private Long id;
}
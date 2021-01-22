package com.danielarrais.algafood.api.dto.input.grupo;

import com.danielarrais.algafood.api.dto.input.permissao.PermissaoIdInput;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoInput {
    @NotBlank
    private String nome;

    @NotEmpty
    private List<@Valid PermissaoIdInput> permissoes;
}
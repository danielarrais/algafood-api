package com.danielarrais.algafood.api.v1.dto.input.grupo;

import com.danielarrais.algafood.api.v1.dto.input.permissao.PermissaoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ApiModelProperty(required = true, example = "Responsável por restaurante")
    private String nome;

    @NotEmpty
    private List<@Valid PermissaoIdInput> permissoes;
}
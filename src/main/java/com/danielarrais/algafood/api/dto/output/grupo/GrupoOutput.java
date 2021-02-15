package com.danielarrais.algafood.api.dto.output.grupo;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GrupoOutput {

    @ApiModelProperty(value = "ID do grupo de permissões", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do grupo de permissões", example = "Fluxo de pedidos")
    private String nome;

    private List<PermissaoOutput> permissoes;
}
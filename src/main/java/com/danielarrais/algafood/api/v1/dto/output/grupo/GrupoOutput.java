package com.danielarrais.algafood.api.v1.dto.output.grupo;

import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class GrupoOutput extends RepresentationModel<GrupoOutput> {

    @ApiModelProperty(value = "ID do grupo de permissões", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do grupo de permissões", example = "Fluxo de pedidos")
    private String nome;

    private List<PermissaoOutput> permissoes;
}
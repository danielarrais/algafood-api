package com.danielarrais.algafood.api.dto.output.permissao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class PermissaoOutput extends RepresentationModel<PermissaoOutput> {

    @ApiModelProperty(value = "ID da permissão", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da permissão", example = "Cancelar pedido")
    private String nome;

    @ApiModelProperty(value = "Descrição da permissão", example = "Permite o usuário cancelar um pedido")
    private String descricao;
}
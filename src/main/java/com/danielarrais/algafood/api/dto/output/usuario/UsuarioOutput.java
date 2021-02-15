package com.danielarrais.algafood.api.dto.output.usuario;

import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class UsuarioOutput {

    @ApiModelProperty(value = "ID do usuário", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do usuário", example = "Lucas Silva")
    private String nome;

    @ApiModelProperty(value = "E-mail do usuário", example = "lucassilva@gmail.com")
    private String email;

    private List<GrupoOutput> grupos;
}
package com.danielarrais.algafood.api.v1.dto.output.usuario;

import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
@Data
public class UsuarioOutput extends RepresentationModel<UsuarioOutput> {

    @ApiModelProperty(value = "ID do usuário", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do usuário", example = "Lucas Silva")
    private String nome;

    @ApiModelProperty(value = "E-mail do usuário", example = "lucassilva@gmail.com")
    private String email;

    private List<GrupoOutput> grupos;
}
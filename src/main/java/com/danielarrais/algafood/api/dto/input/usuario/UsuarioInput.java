package com.danielarrais.algafood.api.dto.input.usuario;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Lucas Silva")
    private String nome;

    @Email
    @NotBlank
    @ApiModelProperty(required = true, example = "lucassilva@gmail.com")
    private String email;

    @NotBlank
    @ApiModelProperty(required = true, example = "46546546546")
    private String senha;

    @NotEmpty
    private List<@Valid GrupoIdInput> grupos;
}
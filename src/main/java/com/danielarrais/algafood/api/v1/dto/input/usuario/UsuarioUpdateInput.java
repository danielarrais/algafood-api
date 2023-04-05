package com.danielarrais.algafood.api.v1.dto.input.usuario;

import com.danielarrais.algafood.api.v1.dto.input.grupo.GrupoIdInput;
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
public class UsuarioUpdateInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Lucas Silva")
    private String nome;

    @Email
    @NotBlank
    @ApiModelProperty(required = true, example = "lucassilva@gmail.com")
    private String email;

    @NotEmpty
    private List<@Valid GrupoIdInput> grupos;
}
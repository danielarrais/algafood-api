package com.danielarrais.algafood.api.v1.dto.input.endereco;

import com.danielarrais.algafood.api.v1.dto.input.cidade.CidadeIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "77065-130")
    private String cep;

    @NotBlank
    @ApiModelProperty(required = true, example = "Rua Mato Grosso de Mathias")
    private String logradouro;
    @ApiModelProperty(example = "SN")
    private String numero;
    @ApiModelProperty(example = "QDR 30 SW 15")
    private String complemento;

    @NotBlank
    @ApiModelProperty(required = true, example = "Jardins Paulista")
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
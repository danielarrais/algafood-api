package com.danielarrais.algafood.api.v1.dto.output.restaurante;

import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeSimpleOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class EnderecoOutput extends RepresentationModel<EnderecoOutput> {

    @ApiModelProperty(value = "CEP do endereço", example = "65960-000")
    private String cep;

    @ApiModelProperty(value = "Logradouro do endereço", example = "Rua Mato Grosso e Mathias")
    private String logradouro;

    @ApiModelProperty(value = "Número do endereço", example = "SN")
    private String numero;

    @ApiModelProperty(value = "Complemento do endereço", example = "APT N. 06 ANDAR 07")
    private String complemento;

    @ApiModelProperty(value = "Bairro do endereço", example = "Centro")
    private String bairro;

    private CidadeSimpleOutput cidade;
}
package com.danielarrais.algafood.api.dto.output.restaurante;

import com.danielarrais.algafood.api.dto.output.cidade.CidadeOutput;
import lombok.Data;


@Data
public class EnderecoOutput {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeOutput cidade;
}
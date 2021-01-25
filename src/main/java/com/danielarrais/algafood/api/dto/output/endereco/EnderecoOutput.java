package com.danielarrais.algafood.api.dto.output.endereco;

import com.danielarrais.algafood.api.dto.output.cidade.CidadeSimpleOutput;
import lombok.Data;


@Data
public class EnderecoOutput {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeSimpleOutput cidade;
}
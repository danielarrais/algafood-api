package com.danielarrais.algafood.api.dto.input.endereco;

import com.danielarrais.algafood.api.dto.input.cidade.CidadeIdInput;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoInput {
    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;
    private String numero;
    private String complemento;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
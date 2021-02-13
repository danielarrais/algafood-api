package com.danielarrais.algafood.api.dto.input.restaurante;

import com.danielarrais.algafood.api.dto.input.cozinha.CozinhaIdInput;
import com.danielarrais.algafood.api.dto.input.endereco.EnderecoInput;
import com.danielarrais.algafood.api.dto.input.formaPagamento.FormaPagamentoIdInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteInput {
    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal taxaFrete;
    private Boolean ativo;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;

    @NotEmpty
    private List<@Valid FormaPagamentoIdInput> formasPagamento;

    @Valid
    private EnderecoInput endereco;
}

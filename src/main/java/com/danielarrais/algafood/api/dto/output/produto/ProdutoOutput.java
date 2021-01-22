package com.danielarrais.algafood.api.dto.output.produto;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteFullOutput;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Data
public class ProdutoOutput {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo = true;
    private RestauranteFullOutput restaurante;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}
package com.danielarrais.algafood.api.dto.output.restaurante;

import lombok.Data;

@Data
public class FotoProdutoOutput {
    private Long tamanho;
    private String contentType;
    private String descricao;
    private String nomeArquivo;
}

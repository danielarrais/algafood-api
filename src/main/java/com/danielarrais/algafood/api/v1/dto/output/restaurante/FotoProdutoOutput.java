package com.danielarrais.algafood.api.v1.dto.output.restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;

@Data
public class FotoProdutoOutput extends RepresentationModel<FotoProdutoOutput> {

    @ApiModelProperty(value = "Tamanho do arquivo (em Bytes)", example = "5000000")
    private Long tamanho;

    @ApiModelProperty(value = "Tipo de conteúdo do arquivo", example = MediaType.IMAGE_JPEG_VALUE)
    private String contentType;

    @ApiModelProperty(value = "Descrição do arquivo", example = "Abobora vermelha")
    private String descricao;

    @ApiModelProperty(value = "Nome do arquivo", example = "abobora_vermelha.jpg")
    private String nomeArquivo;
}

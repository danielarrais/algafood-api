package com.danielarrais.algafood.api.dto.input.restaurante;

import com.danielarrais.algafood.core.validation.FileContentType;
import com.danielarrais.algafood.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Data
public class ProdutoFotoInput {
    @NotBlank(message = "A descrição arquivo é obrigatório")
    @ApiModelProperty(required = true, name = "Descrição da foto", example = "Foto do hamburguer colorido")
    private String descricao;

    @ApiModelProperty(hidden = true)
    @FileSize(max = "500KB")
    @NotNull(message = "O arquivo é obrigatório")
    @FileContentType(allowed = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
    private MultipartFile arquivo;
}

package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.service.FotoProdutoService;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class
RestauranteFotoProdutoController {
    private final FotoProdutoService fotoProdutoService;

    public RestauranteFotoProdutoController(FotoProdutoService fotoProdutoService) {
        this.fotoProdutoService = fotoProdutoService;
    }

    @SneakyThrows
    @PutMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                               @PathVariable Long produtoId,
                               @Valid ProdutoFotoInput produtoFotoInput) {
        var file = produtoFotoInput.getArquivo();

        var fotoProduto = FotoProduto.builder()
                .nomeArquivo(file.getOriginalFilename())
                .descricao(produtoFotoInput.getDescricao())
                .contentType(file.getContentType())
                .tamanho(file.getSize())
                .build();

        fotoProdutoService.salvar(restauranteId, produtoId, fotoProduto, file.getInputStream());
    }
}
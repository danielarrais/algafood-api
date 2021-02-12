package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.service.FotoProdutoService;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;


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

    @GetMapping
    public FotoProdutoOutput buscar(@PathVariable Long restauranteId,
                                    @PathVariable Long produtoId) {
        var fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return mapper(fotoProduto, FotoProdutoOutput.class);
    }
}
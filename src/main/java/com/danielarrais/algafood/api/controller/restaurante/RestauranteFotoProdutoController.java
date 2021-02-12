package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.service.FotoProdutoService;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoOutput buscar(@PathVariable Long restauranteId,
                                    @PathVariable Long produtoId) {
        var fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return mapper(fotoProduto, FotoProdutoOutput.class);
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<InputStreamResource> downloadFoto(@PathVariable Long restauranteId,
                                                            @PathVariable Long produtoId,
                                                            @RequestHeader(name = "Accept") String mediaTypeName) {
        validMediaType(mediaTypeName);

        var fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        var arquivoFoto = fotoProdutoService.download(fotoProduto.getNomeArquivo());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(fotoProduto.getContentType()))
                .body(new InputStreamResource(arquivoFoto));
    }

    private void validMediaType(String mediaTypeName) throws HttpMediaTypeNotAcceptableException {
        var mediaType = MediaType.parseMediaType(mediaTypeName);
        var allowedMediaTypes = Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG);

        var allowed =
                allowedMediaTypes.stream()
                .anyMatch(allowedMediaType -> allowedMediaType.isCompatibleWith(mediaType));

         if (!allowed) {
            throw new HttpMediaTypeNotAcceptableException(allowedMediaTypes);
         }
    }

}
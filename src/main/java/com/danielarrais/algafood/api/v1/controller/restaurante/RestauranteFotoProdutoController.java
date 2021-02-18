package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.service.FotoProdutoService;
import com.danielarrais.algafood.domain.service.FotoStorageService.FotoRecuperada;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController implements RestauranteFotoProdutoControllerOAS {
    private final FotoProdutoService fotoProdutoService;

    public RestauranteFotoProdutoController(FotoProdutoService fotoProdutoService) {
        this.fotoProdutoService = fotoProdutoService;
    }

    @SneakyThrows
    @PutMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId,
                              @Valid ProdutoFotoInput produtoFotoInput,
                              @RequestPart(required = false) MultipartFile arquivo) {
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
    public ResponseEntity<?> downloadFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId,
                                          @RequestHeader(name = "Accept") String mediaTypeName) {
        validMediaType(mediaTypeName);

        try {
            var fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
            var fotoRecuperada = fotoProdutoService.download(fotoProduto.getNomeArquivo());

            return responseFotoRecuperada(fotoRecuperada, fotoProduto.getContentType());
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> responseFotoRecuperada(FotoRecuperada fotoRecuperada, String contentType) {
        if (fotoRecuperada.temUrl()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                    .build();
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(contentType))
                    .body(new InputStreamResource(fotoRecuperada.getInputStream()));
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId,
                        @PathVariable Long produtoId) {
        fotoProdutoService.deletar(restauranteId, produtoId);
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
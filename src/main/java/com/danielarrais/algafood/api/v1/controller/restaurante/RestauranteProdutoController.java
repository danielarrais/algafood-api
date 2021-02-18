package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.assembler.restaurante.ProdutoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.restaurante.ProdutoInput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.ProdutoOutput;
import org.springframework.http.MediaType;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.service.ProdutoService;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOAS {
    private final RestauranteService restauranteService;
    private final ProdutoService produtoService;
    private final ProdutoOutputAssembler produtoOutputAssembler;

    public RestauranteProdutoController(RestauranteService restauranteService,
                                        ProdutoService produtoService,
                                        ProdutoOutputAssembler produtoOutputAssembler) {
        this.restauranteService = restauranteService;
        this.produtoService = produtoService;
        this.produtoOutputAssembler = produtoOutputAssembler;
    }

    @GetMapping()
    public CollectionModel<ProdutoOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return produtoOutputAssembler.toCollectionModel(restauranteId, restaurante.getProdutos());
    }

    @PostMapping
    public void adicionar(@PathVariable Long restauranteId,
                          @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.salvar(restauranteId, produto);
    }

    @GetMapping("/{id}")
    public ProdutoOutput buscar(@PathVariable Long id,
                                @PathVariable Long restauranteId) {
        var produto = produtoService.buscarObrigatorio(id, restauranteId);
        return mapper(produto, ProdutoOutput.class);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id,
                          @PathVariable Long restauranteId,
                          @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.atualizar(id, restauranteId, produto);
    }
}
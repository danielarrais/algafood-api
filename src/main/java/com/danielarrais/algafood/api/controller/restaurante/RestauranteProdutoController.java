package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.ProdutoOutput;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.service.ProdutoService;
import com.danielarrais.algafood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
    private final RestauranteService restauranteService;
    private final ProdutoService produtoService;

    public RestauranteProdutoController(RestauranteService restauranteService, ProdutoService produtoService) {
        this.restauranteService = restauranteService;
        this.produtoService = produtoService;
    }

    @GetMapping()
    public List<ProdutoOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return mapper(restaurante.getProdutos(), ProdutoOutput.class);
    }

    @PostMapping
    public void adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.salvar(restauranteId, produto);
    }

    @GetMapping("/{id}")
    public ProdutoOutput buscar(@PathVariable Long id, @PathVariable Long restauranteId) {
        var produto = produtoService.buscarObrigatorio(id, restauranteId);
        return mapper(produto, ProdutoOutput.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.atualizar(id, restauranteId, produto);
    }
}
package com.danielarrais.algafood.api.v1.assembler.restaurante;

import com.danielarrais.algafood.api.v1.controller.restaurante.RestauranteProdutoController;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.ProdutoOutput;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;

@Component
public class ProdutoOutputAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoOutput> {

    public ProdutoOutputAssembler() {
        super(RestauranteProdutoController.class, ProdutoOutput.class);
    }

    @Override
    public ProdutoOutput toModel(Produto produto) {
        var produtoDTO = ModelMapperUtils.mapper(produto, ProdutoOutput.class);
        var restaurante = produto.getRestaurante();

        produtoDTO.add(linkBuscarProduto(restaurante.getId(), produto.getId()));
        produtoDTO.add(linkFotoProduto(restaurante.getId(), produto.getId()));
        produtoDTO.add(linkProdutos(restaurante.getId()));

        return produtoDTO;
    }

    public CollectionModel<ProdutoOutput> toCollectionModel(Long idRestaurante, Iterable<? extends Produto> entities) {
        return super.toCollectionModel(entities).add(linkProdutos(idRestaurante).withSelfRel());
    }
}

package com.danielarrais.algafood.domain.repository.custom;

import com.danielarrais.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryCustom {
    FotoProduto saveAndFlush(FotoProduto fotoProduto);
    void delete(FotoProduto fotoProduto);
}


package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.repository.ProdutoRepository;
import com.danielarrais.algafood.domain.service.FotoStorageService.Foto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
public class FotoProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final FotoStorageService fotoStorageService;

    public FotoProdutoService(ProdutoRepository fotoProdutoRepository,
                              ProdutoService produtoService, FotoStorageService fotoStorageService) {
        this.produtoRepository = fotoProdutoRepository;
        this.produtoService = produtoService;
        this.fotoStorageService = fotoStorageService;
    }

    @Transactional
    public void salvar(Long restauranteId, Long produtoId,
                       FotoProduto fotoProduto, InputStream conteudoArquivo) {
        var produto = produtoService.buscarObrigatorio(produtoId, restauranteId);
        var fotoProdutoOld = produtoRepository.findFotoById(restauranteId, produtoId);
        var novoNome = fotoStorageService.gerarNomeArquivo(fotoProduto.getNomeArquivo());

        fotoProdutoOld.ifPresent(foto -> {
            produtoRepository.delete(foto);
            fotoStorageService.delete(foto.getNomeArquivo());
        });

        fotoProduto.setProduto(produto);
        fotoProduto.setNomeArquivo(novoNome);

        var foto = Foto.builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(conteudoArquivo).build();

        produtoRepository.saveAndFlush(fotoProduto);
        fotoStorageService.storage(foto);
    }

    public FotoProduto buscar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Foto do produto", produtoId);
        });
    }
}
package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(long cozinhaId) {
        return cozinhaRepository.buscar(cozinhaId);
    }

    public void salvar(Cozinha cozinha) {
        cozinhaRepository.salvar(cozinha);
    }

    public void remover(Cozinha cozinha) {
        cozinhaRepository.remover(cozinha);
    }
}

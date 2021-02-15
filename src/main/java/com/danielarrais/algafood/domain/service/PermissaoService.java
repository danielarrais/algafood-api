package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.repository.PermissaoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;

@Service
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    public Page<Permissao> listar(Pageable pageable) {
        return permissaoRepository.findAll(pageable);
    }

    public Optional<Permissao> buscar(long permissaoId) {
        return permissaoRepository.findById(permissaoId);
    }

    public Permissao buscarObrigatorio(long permissao) {
        return buscar(permissao).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Permissão", permissao);
        });
    }

    @Transactional
    public void salvar(Permissao permissao) {
        permissaoRepository.save(permissao);
    }

    @Transactional
    public void atualizar(Long id, Permissao permissao) {
        var permissaoAtual = buscarObrigatorio(id);

        copyNonNullValues(permissao, permissaoAtual);
        permissaoRepository.save(permissaoAtual);
    }

    @Transactional
    public void remover(Long id) {
        var permissao = buscarObrigatorio(id);

        try {
            permissaoRepository.delete(permissao);
            permissaoRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}
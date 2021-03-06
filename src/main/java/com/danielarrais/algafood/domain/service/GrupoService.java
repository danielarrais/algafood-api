package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.repository.GrupoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;
    private final PermissaoService permissaoService;

    public GrupoService(GrupoRepository grupoRepository, PermissaoService permissaoService) {
        this.grupoRepository = grupoRepository;
        this.permissaoService = permissaoService;
    }

    public Page<Grupo> listar(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    public Optional<Grupo> buscar(long grupoId) {
        return grupoRepository.findById(grupoId);
    }

    public Grupo buscarObrigatorio(long grupoId) {
        return buscar(grupoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Grupo", grupoId);
        });
    }

    @Transactional
    public void salvar(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @Transactional
    public void atualizar(Long id, Grupo grupo) {
        var grupoAtual = buscarObrigatorio(id);

        copyNonNullValues(grupo, grupoAtual);
        grupoRepository.save(grupoAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException("Grupo",id);
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long idPermissao) {
        var grupo = buscarObrigatorio(grupoId);
        var permissao = permissaoService.buscarObrigatorio(idPermissao);

        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long idPermissao) {
        var grupo = buscarObrigatorio(grupoId);
        var permissao = permissaoService.buscarObrigatorio(idPermissao);

        grupo.removerPermissao(permissao);
    }
}
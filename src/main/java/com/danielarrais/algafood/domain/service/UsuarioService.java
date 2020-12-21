package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.repository.UsuarioRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscar(long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    @SneakyThrows
    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void atualizar(Long id, Usuario usuario) {
        buscar(id).map(usuarioAtual -> {
            BeanUtils.copyProperties(usuario, usuarioAtual, "id");
            return usuarioRepository.save(usuarioAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(usuarioAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, usuarioAtual);
            return usuarioRepository.save(usuarioAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        }
    }
}
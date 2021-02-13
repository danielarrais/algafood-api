package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.NegocioException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.repository.UsuarioRepository;
import com.danielarrais.algafood.domain.service.validation.UsuarioValidation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidation usuarioValidation;
    private final GrupoService grupoService;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioValidation usuarioValidation, GrupoService grupoService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidation = usuarioValidation;
        this.grupoService = grupoService;
    }

    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> buscar(long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    public Usuario buscarObrigatorio(long usuarioId) {
        return buscar(usuarioId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Usuário", usuarioId);
        });
    }

    @Transactional
    public void salvar(Usuario usuario) {
        this.usuarioValidation.validate(usuario);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarObrigatorio(id);

        if (!usuario.isSenhaIgual(senhaAtual)) {
            throw new NegocioException("A senha atual informada não é válida");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void atualizar(Long id, Usuario usuario) {
        var usuarioAtual = buscarObrigatorio(id);

        copyNonNullValues(usuario, usuarioAtual);
        this.usuarioValidation.validate(usuarioAtual);
        usuarioRepository.save(usuarioAtual);
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var usuarioAtual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, usuarioAtual);
        this.usuarioValidation.validate(usuarioAtual);
        usuarioRepository.save(usuarioAtual);
    }

    @Transactional
    public void remover(Long id) {
        var usuario = buscarObrigatorio(id);

        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long idGrupo) {
        var usuario = buscarObrigatorio(usuarioId);
        var grupo = grupoService.buscarObrigatorio(idGrupo);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long idGrupo) {
        var usuario = buscarObrigatorio(usuarioId);
        var grupo = grupoService.buscarObrigatorio(idGrupo);

        usuario.removerGrupo(grupo);
    }
}
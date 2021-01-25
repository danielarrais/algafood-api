package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.input.usuario.UsuarioInput;
import com.danielarrais.algafood.api.dto.input.usuario.UsuarioSenhaInput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public List<UsuarioOutput> listar() {
        var usuarios = usuarioService.listar();
        return mapper(usuarios, UsuarioOutput.class);
    }

    @GetMapping("/{id}")
    public UsuarioOutput buscar(@PathVariable Long id) {
        var usuario = usuarioService.buscarObrigatorio(id);
        return mapper(usuario, UsuarioOutput.class);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput) {
        usuarioService.alterarSenha(usuarioId, usuarioSenhaInput.getSenhaAtual(), usuarioSenhaInput.getNovaSenha());
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        var usuario = mapper(usuarioInput, Usuario.class);
        usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
        var usuario = mapper(usuarioInput, Usuario.class);
        usuarioService.atualizar(id, usuario);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        usuarioService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        usuarioService.remover(id);
    }
}
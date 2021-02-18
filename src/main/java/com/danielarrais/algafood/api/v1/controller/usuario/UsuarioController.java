package com.danielarrais.algafood.api.v1.controller.usuario;

import com.danielarrais.algafood.api.v1.assembler.usuario.UsuarioOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.usuario.UsuarioInput;
import com.danielarrais.algafood.api.v1.dto.input.usuario.UsuarioSenhaInput;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.core.util.MediaTypes;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Usuários")
@RestController
@RequestMapping(path = "/usuarios", produces = MediaTypes.APPLICATION_ALGAFOOD_V1_JSON)
public class UsuarioController implements UsuarioControllerOAS {
    private final UsuarioService usuarioService;
    private final UsuarioOutputAssembler usuarioOutputAssembler;
    private final PagedResourcesAssembler<Usuario> pagedResourcesAssembler;


    public UsuarioController(UsuarioService usuarioService, UsuarioOutputAssembler usuarioOutputAssembler, PagedResourcesAssembler<Usuario> pagedResourcesAssembler) {
        this.usuarioService = usuarioService;
        this.usuarioOutputAssembler = usuarioOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<UsuarioOutput> listar(Pageable pageable) {
        var usuarios = usuarioService.listar(pageable);
        return pagedResourcesAssembler.toModel(usuarios, usuarioOutputAssembler);
    }

    @GetMapping("/{id}")
    public UsuarioOutput buscar(@PathVariable Long id) {
        var usuario = usuarioService.buscarObrigatorio(id);
        return usuarioOutputAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput) {
        usuarioService.alterarSenha(usuarioId, usuarioSenhaInput.getSenhaAtual(), usuarioSenhaInput.getNovaSenha());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        usuarioService.remover(id);
    }
}
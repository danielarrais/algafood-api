package com.danielarrais.algafood.api.controller.usuario;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOAS {
    private final UsuarioService usuarioService;

    public UsuarioGrupoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public List<PermissaoOutput> listar(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarObrigatorio(usuarioId);
        return mapper(usuario.getGrupos(), PermissaoOutput.class);
    }

    @PutMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long idGrupo) {
        usuarioService.associarGrupo(usuarioId, idGrupo);
    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long idGrupo) {
        usuarioService.desassociarGrupo(usuarioId, idGrupo);
    }
}
package com.danielarrais.algafood.api.v1.controller.usuario;

import com.danielarrais.algafood.api.v1.assembler.grupo.GrupoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import org.springframework.http.MediaType;
import com.danielarrais.algafood.domain.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOAS {
    private final UsuarioService usuarioService;
    private final GrupoOutputAssembler grupoOutputAssembler;

    public UsuarioGrupoController(UsuarioService usuarioService,
                                  GrupoOutputAssembler grupoOutputAssembler) {
        this.usuarioService = usuarioService;
        this.grupoOutputAssembler = grupoOutputAssembler;
    }

    @GetMapping()
    public CollectionModel<GrupoOutput> listar(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarObrigatorio(usuarioId);
        return grupoOutputAssembler.toCollectionModel(usuarioId, usuario.getGrupos());
    }

    @PutMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long idGrupo) {
        usuarioService.associarGrupo(usuarioId, idGrupo);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long idGrupo) {
        usuarioService.desassociarGrupo(usuarioId, idGrupo);

        return ResponseEntity.noContent().build();
    }
}
package com.danielarrais.algafood.api.v1.controller.grupo;

import com.danielarrais.algafood.api.v1.assembler.permissao.PermissaoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.service.GrupoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissoesController implements GrupoPermissoesControllerOAS {
    private final GrupoService grupoService;
    private final PermissaoOutputAssembler permissaoOutputAssembler;

    public GrupoPermissoesController(GrupoService grupoService,
                                     PermissaoOutputAssembler permissaoOutputAssembler) {
        this.grupoService = grupoService;
        this.permissaoOutputAssembler = permissaoOutputAssembler;
    }

    @GetMapping()
    public CollectionModel<PermissaoOutput> listar(@PathVariable Long grupoId) {
        var grupo = grupoService.buscarObrigatorio(grupoId);
        return permissaoOutputAssembler.toCollectionModel(grupoId, grupo.getPermissoes());
    }

    @PutMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long idPermissao) {
        grupoService.associarPermissao(grupoId, idPermissao);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long idPermissao) {
        grupoService.desassociarPermissao(grupoId, idPermissao);

        return ResponseEntity.noContent().build();
    }
}
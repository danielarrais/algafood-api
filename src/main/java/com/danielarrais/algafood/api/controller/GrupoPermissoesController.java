package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {
    private final GrupoService grupoService;

    public GrupoPermissoesController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping()
    public List<PermissaoOutput> listar(@PathVariable Long grupoId) {
        var grupo = grupoService.buscarObrigatorio(grupoId);
        return mapper(grupo.getPermissoes(), PermissaoOutput.class);
    }

    @PutMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long idPermissao) {
        grupoService.associarPermissao(grupoId, idPermissao);
    }

    @DeleteMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long idPermissao) {
        grupoService.desassociarPermissao(grupoId, idPermissao);
    }
}
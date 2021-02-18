package com.danielarrais.algafood.api.v1.controller.grupo;

import com.danielarrais.algafood.api.v1.assembler.grupo.GrupoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.service.GrupoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/grupos", produces = "application/vnd.algafood.v1+json")
public class GrupoController implements GrupoControllerOAS {
    private final GrupoService grupoService;
    private final GrupoOutputAssembler grupoOutputAssembler;
    private final PagedResourcesAssembler<Grupo> pagedResourcesAssembler;

    public GrupoController(GrupoService grupoService,
                           GrupoOutputAssembler grupoOutputAssembler,
                           PagedResourcesAssembler<Grupo> pagedResourcesAssembler) {
        this.grupoService = grupoService;
        this.grupoOutputAssembler = grupoOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<GrupoOutput> listar(Pageable pageable) {
        var grupos = grupoService.listar(pageable);
        return pagedResourcesAssembler.toModel(grupos, grupoOutputAssembler);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public GrupoOutput buscar(@PathVariable Long id) {
        var grupo = grupoService.buscarObrigatorio(id);
        return grupoOutputAssembler.toModel(grupo);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado", response = Problem.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = mapper(grupoInput, Grupo.class);
        grupoService.salvar(grupo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public void atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        var grupo = mapper(grupoInput, Grupo.class);
        grupoService.atualizar(id, grupo);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo de permissão excluido"),
            @ApiResponse(code = 404, message = "Grupo de permissão não encontrado", response = Problem.class)
    })
    public void remover(@PathVariable Long id) {
        grupoService.remover(id);
    }
}
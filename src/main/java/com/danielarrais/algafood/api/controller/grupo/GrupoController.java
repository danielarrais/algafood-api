package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.service.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping()
    public Page<GrupoOutput> listar(Pageable pageable) {
        var grupos = grupoService.listar(pageable);
        return mapper(grupos, GrupoOutput.class);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public GrupoOutput buscar(@PathVariable Long id) {
        var grupo = grupoService.buscarObrigatorio(id);
        return mapper(grupo, GrupoOutput.class);
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        grupoService.atualizar(id, valores);
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
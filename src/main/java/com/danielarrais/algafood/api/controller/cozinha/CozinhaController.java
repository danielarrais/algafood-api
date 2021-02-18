package com.danielarrais.algafood.api.controller.cozinha;

import com.danielarrais.algafood.api.assembler.cozinha.CozinhaFullOutputAssembler;
import com.danielarrais.algafood.api.assembler.cozinha.CozinhaOutputAssembler;
import com.danielarrais.algafood.api.dto.input.cozinha.CozinhaInput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.service.CozinhaService;
import io.swagger.annotations.Api;
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

@Api(tags = "Cozinhas")
@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
    private final CozinhaService cozinhaService;
    private final CozinhaOutputAssembler cozinhaOutputAssembler;
    private final CozinhaFullOutputAssembler cozinhaFullOutputAssembler;
    private final PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    public CozinhaController(CozinhaService cozinhaService,
                             CozinhaOutputAssembler cozinhaOutputAssembler,
                             CozinhaFullOutputAssembler cozinhaFullOutputAssembler, PagedResourcesAssembler<Cozinha> pagedResourcesAssembler) {
        this.cozinhaService = cozinhaService;
        this.cozinhaOutputAssembler = cozinhaOutputAssembler;
        this.cozinhaFullOutputAssembler = cozinhaFullOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<CozinhaOutput> listar(Pageable pageable) {
        var cozinhas = cozinhaService.listar(pageable);
        return pagedResourcesAssembler.toModel(cozinhas, cozinhaOutputAssembler);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public CozinhaFullOutput buscar(@PathVariable Long id) {
        var cozinha = cozinhaService.buscarObrigatorio(id);
        return cozinhaFullOutputAssembler.toModel(cozinha);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada", response = Problem.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = mapper(cozinhaInput, Cozinha.class);
        cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public void atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = mapper(cozinhaInput, Cozinha.class);
        cozinhaService.atualizar(id, cozinha);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluida"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public void remover(@PathVariable Long id) {
        cozinhaService.remover(id);
    }
}
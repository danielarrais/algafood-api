package com.danielarrais.algafood.api.v2.controller.cidade;

import com.danielarrais.algafood.api.v2.assembler.cidade.CidadeOutputAssemblerV2;
import com.danielarrais.algafood.api.v2.dto.input.cidade.CidadeInputV2;
import com.danielarrais.algafood.api.v2.dto.output.cidade.CidadeOutputV2;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerOASV2 {
    private final CidadeService cidadeService;
    private final CidadeOutputAssemblerV2 cidadeOutputAssembler;
    private final PagedResourcesAssembler<Cidade> pagedResourcesAssembler;

    public CidadeControllerV2(CidadeService cidadeService, CidadeOutputAssemblerV2 cidadeOutputAssembler, PagedResourcesAssembler<Cidade> pagedResourcesAssembler) {
        this.cidadeService = cidadeService;
        this.cidadeOutputAssembler = cidadeOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public CollectionModel<CidadeOutputV2> listar(Pageable pageable) {
        var cidades = cidadeService.listar(pageable);
        return pagedResourcesAssembler.toModel(cidades, cidadeOutputAssembler);
    }

    @GetMapping("/{id}")
    public CidadeOutputV2 buscar(@PathVariable Long id) {
        var cidade = cidadeService.buscarObrigatorio(id);
        return cidadeOutputAssembler.toModel(cidade);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id,
                          @RequestBody @Valid CidadeInputV2 cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.atualizar(id, cidade);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remover(id);
    }
}
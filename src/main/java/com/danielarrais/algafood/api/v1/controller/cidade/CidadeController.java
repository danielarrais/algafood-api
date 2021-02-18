package com.danielarrais.algafood.api.v1.controller.cidade;

import com.danielarrais.algafood.api.v1.assembler.cidade.CidadeOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.cidade.CidadeInput;
import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeOutput;
import org.springframework.http.MediaType;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOAS {
    private final CidadeService cidadeService;
    private final CidadeOutputAssembler cidadeOutputAssembler;
    private final PagedResourcesAssembler<Cidade> pagedResourcesAssembler;

    public CidadeController(CidadeService cidadeService, CidadeOutputAssembler cidadeOutputAssembler, PagedResourcesAssembler<Cidade> pagedResourcesAssembler) {
        this.cidadeService = cidadeService;
        this.cidadeOutputAssembler = cidadeOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<CidadeOutput> listar(Pageable pageable) {
        var cidades = cidadeService.listar(pageable);
        return pagedResourcesAssembler.toModel(cidades, cidadeOutputAssembler);
    }

    @GetMapping("/{id}")
    public CidadeOutput buscar(@PathVariable Long id) {
        var cidade = cidadeService.buscarObrigatorio(id);
        return cidadeOutputAssembler.toModel(cidade);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id,
                          @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.atualizar(id, cidade);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remover(id);
    }
}
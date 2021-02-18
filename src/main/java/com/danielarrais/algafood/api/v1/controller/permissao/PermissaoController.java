package com.danielarrais.algafood.api.v1.controller.permissao;

import com.danielarrais.algafood.api.v1.assembler.permissao.PermissaoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.permissao.PermissaoInput;
import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.service.PermissaoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/permissoes", produces = "application/vnd.algafood.v1+json")
public class PermissaoController implements PermissaoControllerOAS {
    private final PermissaoService permissaoService;
    private final PermissaoOutputAssembler permissaoOutputAssembler;
    private final PagedResourcesAssembler<Permissao> pagedResourcesAssembler;

    public PermissaoController(PermissaoService permissaoService,
                               PermissaoOutputAssembler permissaoOutputAssembler,
                               PagedResourcesAssembler<Permissao> pagedResourcesAssembler) {
        this.permissaoService = permissaoService;
        this.permissaoOutputAssembler = permissaoOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<PermissaoOutput> listar(Pageable pageable) {
        var permissoes = permissaoService.listar(pageable);
        return pagedResourcesAssembler.toModel(permissoes, permissaoOutputAssembler);
    }

    @GetMapping("/{id}")
    public PermissaoOutput buscar(@PathVariable Long id) {
        var permissao = permissaoService.buscarObrigatorio(id);
        return permissaoOutputAssembler.toModel(permissao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
        var permissao = mapper(permissaoInput, Permissao.class);
        permissaoService.salvar(permissao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid PermissaoInput permissaoInput) {
        var permissao = mapper(permissaoInput, Permissao.class);
        permissaoService.atualizar(id, permissao);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        permissaoService.remover(id);
    }
}
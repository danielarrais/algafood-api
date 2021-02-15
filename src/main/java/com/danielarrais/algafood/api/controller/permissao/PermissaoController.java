package com.danielarrais.algafood.api.controller.permissao;

import com.danielarrais.algafood.api.dto.input.permissao.PermissaoInput;
import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.service.PermissaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOAS {
    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @GetMapping()
    public Page<PermissaoOutput> listar(Pageable pageable) {
        var permissaos = permissaoService.listar(pageable);
        return mapper(permissaos, PermissaoOutput.class);
    }

    @GetMapping("/{id}")
    public PermissaoOutput buscar(@PathVariable Long id) {
        var permissao = permissaoService.buscarObrigatorio(id);
        return mapper(permissao, PermissaoOutput.class);
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
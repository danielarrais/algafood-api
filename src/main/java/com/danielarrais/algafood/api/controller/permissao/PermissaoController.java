package com.danielarrais.algafood.api.controller.permissao;

import com.danielarrais.algafood.api.dto.input.permissao.PermissaoInput;
import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.service.PermissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @GetMapping()
    public List<PermissaoOutput> listar() {
        var permissaos = permissaoService.listar();
        return mapper(permissaos, PermissaoOutput.class);
    }

    @GetMapping("/{id}")
    public PermissaoOutput buscar(@PathVariable Long id) {
        var permissao = permissaoService.buscarObrigatorio(id);
        return mapper(permissao, PermissaoOutput.class);
    }

    @PostMapping
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        permissaoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        permissaoService.remover(id);
    }
}
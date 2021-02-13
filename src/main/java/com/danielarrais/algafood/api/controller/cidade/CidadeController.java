package com.danielarrais.algafood.api.controller.cidade;

import com.danielarrais.algafood.api.dto.input.cidade.CidadeInput;
import com.danielarrais.algafood.api.dto.output.cidade.CidadeOutput;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.service.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping()
    public Page<CidadeOutput> listar(Pageable pageable) {
        var cidades = cidadeService.listar(pageable);
        return mapper(cidades, CidadeOutput.class);
    }

    @GetMapping("/{id}")
    public CidadeOutput buscar(@PathVariable Long id) {
        var cidade = cidadeService.buscarObrigatorio(id);
        return mapper(cidade, CidadeOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.atualizar(id, cidade);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        cidadeService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remover(id);
    }
}
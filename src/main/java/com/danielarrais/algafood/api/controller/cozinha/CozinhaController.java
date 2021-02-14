package com.danielarrais.algafood.api.controller.cozinha;

import com.danielarrais.algafood.api.dto.input.cozinha.CozinhaInput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.service.CozinhaService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Cozinhas")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping()
    public Page<CozinhaOutput> listar(Pageable pageable) {
        var cozinhas = cozinhaService.listar(pageable);
        return mapper(cozinhas, CozinhaOutput.class);
    }

    @GetMapping("/{id}")
    public CozinhaFullOutput buscar(@PathVariable Long id) {
        var cozinha = cozinhaService.buscarObrigatorio(id);
        return mapper(cozinha, CozinhaFullOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = mapper(cozinhaInput, Cozinha.class);
        cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = mapper(cozinhaInput, Cozinha.class);
        cozinhaService.atualizar(id, cozinha);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        cozinhaService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cozinhaService.remover(id);
    }
}
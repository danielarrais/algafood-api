package com.danielarrais.algafood.api.controller.cidade;

import com.danielarrais.algafood.api.dto.input.cidade.CidadeInput;
import com.danielarrais.algafood.api.dto.output.cidade.CidadeOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.service.CidadeService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
@SwaggerDefinition(tags = {})
public class CidadeController {
    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @ApiOperation("Lista as cidades")
    @GetMapping()
    public Page<CidadeOutput> listar(Pageable pageable) {
        var cidades = cidadeService.listar(pageable);
        return mapper(cidades, CidadeOutput.class);
    }

    @ApiOperation("Busca uma cidade pelo ID")
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeOutput buscar(@ApiParam("ID de uma cidade") @PathVariable Long id) {
        var cidade = cidadeService.buscarObrigatorio(id);
        return mapper(cidade, CidadeOutput.class);
    }

    @ApiOperation("Adiciona uma cidade")
    @PostMapping
    public void adicionar(@ApiParam(name = "Corpo", value = "Dados da cidade")
                              @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.salvar(cidade);
    }

    @ApiOperation("Atualiza uma cidade pelo ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@ApiParam("ID de uma cidade") @PathVariable Long id,
                          @ApiParam(name = "Corpo", value = "Novos dados da cidade") @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = mapper(cidadeInput, Cidade.class);
        cidadeService.atualizar(id, cidade);
    }

    @ApiOperation("Atualiza partes de uma cidade pelo ID")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@ApiParam("ID de uma cidade") @PathVariable Long id,
                                 @ApiParam("Novos dados da cidade") @RequestBody @Valid Map<String, Object> valores) {
        cidadeService.atualizar(id, valores);
    }

    @ApiOperation("Deleta uma cidade pelo ID")
    @DeleteMapping("/{id}")
    public void remover(@ApiParam("ID de uma cidade") @PathVariable Long id) {
        cidadeService.remover(id);
    }
}
package com.danielarrais.algafood.api.v1.controller.cidade;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.v1.dto.input.cidade.CidadeInput;
import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeOutput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
interface CidadeControllerOAS {

    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeOutput> listar(Pageable pageable);

    @ApiOperation("Busca uma cidade pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeOutput buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);

    @ApiOperation("Adiciona uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada", response = Problem.class)
    })
    void adicionar(@ApiParam(name = "Corpo", value = "Dados da cidade")
                           CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id,
                   @ApiParam(name = "Corpo", value = "Novos dados da cidade") CidadeInput cidadeInput);

    @ApiOperation("Deleta uma cidade pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluida"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);
}
package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.dto.input.restaurante.RestauranteInput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Restaurantes")
interface RestauranteControllerOAS {

    @ApiOperation("Lista restaurantes")
    CollectionModel<RestauranteSimpleOutput> listar(Pageable pageable);

    @ApiOperation("Busca restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    RestauranteFullOutput buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);

    @ApiOperation("Adiciona restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Restaurante cadastrado", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") RestauranteInput restauranteInput);

    @ApiOperation("Atualiza restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id,
                   @ApiParam("Corpo") RestauranteInput restauranteInput);

    @ApiOperation("Ativa restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> ativar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);

    @ApiOperation("Inativa restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> inativar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);

    @ApiOperation("Ativa restaurantes")
    void ativarBach(@ApiParam(value = "IDs dos restaurantes") List<Long> ids);

    @ApiOperation("Inativa restaurantes")
    void inativarBach(@ApiParam(value = "IDs dos restaurantes") List<Long> ids);

    @ApiOperation("Abre restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> abrir(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);

    @ApiOperation("Fecha restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> fechar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);

    @ApiOperation("Exlui restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante excluido"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long id);
}
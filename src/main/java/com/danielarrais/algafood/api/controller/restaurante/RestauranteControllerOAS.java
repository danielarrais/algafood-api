package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.RestauranteInput;
import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Api(tags = "Restaurantes")
interface RestauranteControllerOAS {

    @ApiOperation("Lista restaurantes")
    Page<RestauranteFullOutput> listar(Pageable pageable);

    @ApiOperation("Busca restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    RestauranteFullOutput buscar(Long id);

    @ApiOperation("Adiciona restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Restaurante cadastrado", response = Problem.class)
    })
    void adicionar(RestauranteInput restauranteInput);

    @ApiOperation("Atualiza restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizar(Long id, RestauranteInput restauranteInput);

    @ApiOperation("Atauliza parcialmente restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiOperation("Ativa restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void ativar(Long id);

    @ApiOperation("Inativa restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void inativar(Long id);

    @ApiOperation("Ativa restaurantes")
    void ativarBach(List<Long> ids);

    @ApiOperation("Inativa restaurantes")
    void inativarBach(List<Long> ids);

    @ApiOperation("Abre restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void abrir(Long id);

    @ApiOperation("Fecha restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void fechar(Long id);

    @ApiOperation("Exlui restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante excluido"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void remover(Long id);
}
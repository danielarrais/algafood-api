package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.RestauranteInput;
import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Api(tags = "Restaurantes")
interface RestauranteControllerOAS {
    Page<RestauranteFullOutput> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    RestauranteFullOutput buscar(Long id);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Restaurante cadastrado", response = Problem.class)
    })
    void adicionar(RestauranteInput restauranteInput);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizar(Long id, RestauranteInput restauranteInput);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void ativar(Long id);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void inativar(Long id);

    void ativarBach(List<Long> ids);

    void inativarBach(List<Long> ids);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void abrir(Long id);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado"),
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void fechar(Long id);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante excluido"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void remover(Long id);
}
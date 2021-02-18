package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "Responsáveis do Restaurante")
interface RestauranteUsuarioResponsavelControllerOAS {

    @ApiOperation("Lista responsáveis de um restaurante")
    @GetMapping()
    CollectionModel<UsuarioSimpleOutput> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Associa responśavel a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID do usuário", example = "1", required = true) Long idUsuario);

    @ApiOperation("Desassocia responśavel a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID do usuário", example = "1", required = true) Long idUsuario);
}
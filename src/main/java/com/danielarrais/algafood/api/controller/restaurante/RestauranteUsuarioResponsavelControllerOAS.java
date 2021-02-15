package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Responsáveis do Restaurante")
interface RestauranteUsuarioResponsavelControllerOAS {

    @ApiOperation("Lista responsáveis de um restaurante")
    @GetMapping()
    List<UsuarioSimpleOutput> listar(@ApiParam("ID do restaurante") Long restauranteId);

    @ApiOperation("Associa responśavel a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void associar(@ApiParam("ID do restaurante") Long restauranteId, @ApiParam("ID do usuário") Long idUsuario);

    @ApiOperation("Desassocia responśavel a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam("ID do restaurante") Long restauranteId, @ApiParam("ID do usuário") Long idUsuario);
}
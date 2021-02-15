package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Responsáveis do Restaurante")
interface RestauranteUsuarioResponsavelControllerOAS {
    @GetMapping()
    List<UsuarioSimpleOutput> listar(Long restauranteId);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void associar(Long restauranteId, Long idUsuario);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao restaurante"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void desassociar(Long restauranteId, Long idUsuario);
}
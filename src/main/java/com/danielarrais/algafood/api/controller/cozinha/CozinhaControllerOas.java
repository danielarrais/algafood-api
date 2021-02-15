package com.danielarrais.algafood.api.controller.cozinha;

import com.danielarrais.algafood.api.dto.input.cozinha.CozinhaInput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Map;

@Api(tags = "Cozinhas")
interface CozinhaControllerOas {
    Page<CozinhaOutput> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaFullOutput buscar(Long id);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada", response = Problem.class)
    })
    void adicionar(CozinhaInput cozinhaInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void atualizar(Long id, CozinhaInput cozinhaInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluida"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(Long id);
}
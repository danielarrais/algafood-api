package com.danielarrais.algafood.api.controller.permissao;

import com.danielarrais.algafood.api.dto.input.permissao.PermissaoInput;
import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Api(tags = "Permissões")
interface PermissaoControllerOAS {

    Page<PermissaoOutput> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da permissão inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    PermissaoOutput buscar(Long id);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Permissão cadastrada", response = Problem.class)
    })
    void adicionar(PermissaoInput permissaoInput);
    
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão atualizada"),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    void atualizar(Long id, PermissaoInput permissaoInput);
    
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão atualizada"),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão excluida"),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    void remover(Long id);
}
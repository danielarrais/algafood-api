package com.danielarrais.algafood.api.controller.usuario;

import com.danielarrais.algafood.api.dto.input.usuario.UsuarioInput;
import com.danielarrais.algafood.api.dto.input.usuario.UsuarioSenhaInput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Api(tags = "Usuários")
interface UsuarioControllerOAS {

    @ApiOperation("Lista usuários")
    Page<UsuarioOutput> listar(Pageable pageable);

    UsuarioOutput buscar(Long id);

    @ApiOperation("Altera senha do usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(Long usuarioId, UsuarioSenhaInput usuarioSenhaInput);

    @ApiOperation("Adiciona usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado")
    })
    void adicionar(UsuarioInput usuarioInput);

    @ApiOperation("Atualiza usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void atualizar(Long id, UsuarioInput usuarioInput);

    @ApiOperation("Atualiza parcialmente usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiOperation("Exclui usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário excluido"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void remover(Long id);
}
package com.danielarrais.algafood.api.controller.usuario;

import com.danielarrais.algafood.api.dto.input.usuario.UsuarioInput;
import com.danielarrais.algafood.api.dto.input.usuario.UsuarioSenhaInput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Usuários")
interface UsuarioControllerOAS {

    @ApiOperation("Lista usuários")
    Page<UsuarioOutput> listar(Pageable pageable);

    UsuarioOutput buscar(@ApiParam(value = "ID do usuário") Long id);

    @ApiOperation("Altera senha do usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(@ApiParam(value = "ID do usuário") Long usuarioId, @ApiParam("Corpo") UsuarioSenhaInput usuarioSenhaInput);

    @ApiOperation("Adiciona usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado")
    })
    void adicionar(@ApiParam("Corpo") UsuarioInput usuarioInput);

    @ApiOperation("Atualiza usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID do usuário") Long id, @ApiParam("Corpo") UsuarioInput usuarioInput);

    @ApiOperation("Exclui usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário excluido"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do usuário") Long id);
}
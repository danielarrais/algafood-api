package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "Grupos de Permissões")
interface GrupoControllerOAS {
    Page<GrupoOutput> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoOutput buscar(Long id);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado", response = Problem.class)
    })
    void adicionar(GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void atualizar(Long id, GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo de permissão excluido"),
            @ApiResponse(code = 404, message = "Grupo de permissão não encontrado", response = Problem.class)
    })
    void remover(Long id);
}
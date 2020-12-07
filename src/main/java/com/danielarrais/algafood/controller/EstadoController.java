package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping()
    public List<Estado> listar() {
        return estadoRepository.all();
    }
}


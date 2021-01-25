package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.NegocioException;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidation {
    private final UsuarioRepository usuarioRepository;
    private final ComumValidation comumValidation;

    public UsuarioValidation(UsuarioRepository usuarioRepository, ComumValidation comumValidation
    ) {
        this.usuarioRepository = usuarioRepository;
        this.comumValidation = comumValidation;
    }

    public void validate(Usuario usuario) {
        validateUniqueEmail(usuario);
    }

    private void validateUniqueEmail(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario)){
            throw new NegocioException("O email informado já está sendo utilizado");
        }
    }

}

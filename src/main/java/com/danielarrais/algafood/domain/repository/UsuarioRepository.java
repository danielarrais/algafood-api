package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends BaseSimpleJpaRepository<Usuario, Long> {

    @Query("select (count(u.id)>0) from Usuario u " +
            "where (:#{#usuario.id} is null or u.id <> :#{#usuario.id}) " +
            "and u.email = :#{#usuario.email}")
    boolean existsByEmail(Usuario usuario);
}


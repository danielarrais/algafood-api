package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.core.validation.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {
    @Id
    @NotNull(groups = Groups.OnlyId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Valid
    @ConvertGroup(to = Groups.OnlyId.class)
    @ManyToOne
    private Estado estado;
}
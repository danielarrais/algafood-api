package com.danielarrais.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;
}

package com.danielarrais.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long id;

    private Long tamanho;
    private String contentType;
    private String descricao;
    private String nomeArquivo;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Produto produto;
}

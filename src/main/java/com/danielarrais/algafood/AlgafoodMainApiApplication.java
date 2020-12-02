package com.danielarrais.algafood;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.jpa.CadastroCozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class AlgafoodMainApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha =  Cozinha
                .builder()
                .nome("President Jackin")
                .build();

        Cozinha novaCozinha = cadastroCozinha.adicionarOuAtualizar(cozinha);
        Long idNovaCozinha = novaCozinha.getId();

        cadastroCozinha.listar().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });

        Cozinha cozinhaRecemInserida = cadastroCozinha.buscarPorId(idNovaCozinha);

        System.out.println("Cozinha Recém Inserida: " + cozinhaRecemInserida.getNome());

        cozinhaRecemInserida.setNome("President Jackin 2");
    }
}

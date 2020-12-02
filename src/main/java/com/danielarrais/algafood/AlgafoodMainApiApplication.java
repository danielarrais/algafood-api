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

        Cozinha cozinha2 =  Cozinha
                .builder()
                .nome("President Jackin2")
                .build();

        Cozinha novaCozinha = cadastroCozinha.salvar(cozinha);
        Cozinha novaCozinha2 = cadastroCozinha.salvar(cozinha2);

        Long idNovaCozinha = novaCozinha.getId();

        cadastroCozinha.listar().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });

        System.out.println("=================================================");

        Cozinha cozinhaRecemInserida = cadastroCozinha.buscarPorId(idNovaCozinha);

        System.out.println("Cozinha Inserida: " + cozinhaRecemInserida.getNome());

        System.out.println("=================================================");

        cozinhaRecemInserida.setNome("President Jackin 2");

        Cozinha cozinhaAtualizada = cadastroCozinha.salvar(cozinhaRecemInserida);

        System.out.println("Cozinha Atualizada: " + cozinhaAtualizada.getNome());

        System.out.println("=================================================");

        cadastroCozinha.remover(cozinhaAtualizada);

        System.out.println("=================================================");

        cadastroCozinha.listar().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });
    }
}

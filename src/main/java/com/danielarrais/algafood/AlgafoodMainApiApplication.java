package com.danielarrais.algafood;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class AlgafoodMainApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha =  Cozinha
                .builder()
                .nome("President Jackin")
                .build();

        Cozinha cozinha2 =  Cozinha
                .builder()
                .nome("President Jackin2")
                .build();

        Cozinha novaCozinha = cozinhas.add(cozinha);
        Cozinha novaCozinha2 = cozinhas.add(cozinha2);

        Long idNovaCozinha = novaCozinha.getId();

        cozinhas.all().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });

        System.out.println("=================================================");

        Cozinha cozinhaRecemInserida = cozinhas.find(idNovaCozinha);
        System.out.println("Cozinha Inserida: " + cozinhaRecemInserida.getNome());

        System.out.println("=================================================");

        cozinhaRecemInserida.setNome("President Jackin 2");
        Cozinha cozinhaAtualizada = cozinhas.add(cozinhaRecemInserida);
        System.out.println("Cozinha Atualizada: " + cozinhaAtualizada.getNome());

        System.out.println("=================================================");

        cozinhas.remove(cozinhaAtualizada);

        System.out.println("=================================================");

        cozinhas.all().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });
    }
}

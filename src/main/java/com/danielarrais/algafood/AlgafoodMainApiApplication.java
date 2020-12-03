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

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha =  Cozinha
                .builder()
                .nome("President Jackin")
                .build();

        Cozinha cozinha2 =  Cozinha
                .builder()
                .nome("President Jackin2")
                .build();

        Cozinha novaCozinha = cozinhaRepository.add(cozinha);
        Cozinha novaCozinha2 = cozinhaRepository.add(cozinha2);

        Long idNovaCozinha = novaCozinha.getId();

        cozinhaRepository.all().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });

        System.out.println("=================================================");

        Cozinha cozinhaRecemInserida = cozinhaRepository.find(idNovaCozinha);
        System.out.println("Cozinha Inserida: " + cozinhaRecemInserida.getNome());

        System.out.println("=================================================");

        cozinhaRecemInserida.setNome("President Jackin 2");
        Cozinha cozinhaAtualizada = cozinhaRepository.add(cozinhaRecemInserida);
        System.out.println("Cozinha Atualizada: " + cozinhaAtualizada.getNome());

        System.out.println("=================================================");

        cozinhaRepository.remove(cozinhaAtualizada);

        System.out.println("=================================================");

        cozinhaRepository.all().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });
    }
}

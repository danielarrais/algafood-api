package com.danielarrais.algafood;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class AlgafoodMainApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
        RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);

        Cozinha cozinhaFrancesa =  Cozinha
                .builder()
                .nome("Francesa")
                .build();

        cozinhaFrancesa = cozinhas.add(cozinhaFrancesa);

        Restaurante restauranteJackin =  Restaurante
                .builder()
                .nome("President Jackin")
                .cozinha(cozinhaFrancesa)
                .build();

        restauranteJackin = restaurantes.add(restauranteJackin);

        cozinhas.all().stream().forEach((entity) -> {
            System.out.println(entity.getNome());
        });

        System.out.println("===========================================================");

        System.out.println("Restaurante Inserid: " + cozinhaFrancesa.getNome());
        System.out.println("Cozinha do Restaurante Inserido: " + restauranteJackin.getCozinha().getNome());

        System.out.println("================= MUDANDO NOME DO RESTAURANTE =============");

        restauranteJackin.setNome("President Jackin - II");
        restauranteJackin = restaurantes.add(restauranteJackin);
        System.out.println("Novo nome do restaurante: " + restauranteJackin.getNome());

        System.out.println("================== REMOVENDO RESTAURANTE ==================");

        restaurantes.remove(restauranteJackin);
        cozinhas.remove(cozinhaFrancesa);

        System.out.println("=================================================");
    }
}

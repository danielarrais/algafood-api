package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RestauranteValidation {
    private final CozinhaRepository cozinhaRepository;
    private final CidadeRepository cidadeRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final ComumValidation comumValidation;

    public RestauranteValidation(CozinhaRepository cozinhaRepository,
                                 CidadeRepository cidadeRepository,
                                 FormaPagamentoRepository formaPagamentoRepository,
                                 ComumValidation comumValidation
    ) {
        this.cozinhaRepository = cozinhaRepository;
        this.cidadeRepository = cidadeRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.comumValidation = comumValidation;
    }

    public void validate(Restaurante restaurante) {
        this.validateTheCozinhaExistence(restaurante);
        this.validateTheCidadeExistence(restaurante);
        this.validateTheFormasPagamentoExistence(restaurante);
    }

    public void smartValidate(Restaurante restaurante) {
        this.comumValidation.smartValidate(restaurante);
    }

    private void validateTheCozinhaExistence(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        boolean existsCozinha = cozinhaRepository.existsById(cozinhaId);

        if (!existsCozinha) {
            throw new DependenciaNaoEncontradaException("Cozinha", cozinhaId);
        }
    }

    private void validateTheFormasPagamentoExistence(Restaurante restaurante) {
        restaurante.getFormasPagamento().forEach(formaPagamento -> {
            Long formaPagamentoId = formaPagamento.getId();
            boolean existsFormaPagamento = formaPagamentoRepository.existsById(formaPagamentoId);

            if (!existsFormaPagamento) {
                throw new DependenciaNaoEncontradaException("Forma de Pagamento", formaPagamentoId);
            }
        });

    }

    private void validateTheCidadeExistence(Restaurante restaurante) {
        if (Objects.isNull(restaurante) ||
                Objects.isNull(restaurante.getEndereco()) ||
                Objects.isNull(restaurante.getEndereco().getCidade())) {
            return;
        }

        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        boolean existsCidade = cidadeRepository.existsById(cidadeId);

        if (!existsCidade) {
            throw new DependenciaNaoEncontradaException("Cidade", cidadeId);
        }
    }
}

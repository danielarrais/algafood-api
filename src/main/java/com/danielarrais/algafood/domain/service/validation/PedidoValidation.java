package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.NegocioException;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class PedidoValidation {

    public PedidoValidation() {
    }

    public void validate(Pedido pedido) {
        validateFormaDePagamento(pedido);
    }

    private void validateFormaDePagamento(Pedido pedido) {
        Restaurante restaurante = pedido.getRestaurante();
        FormaPagamento formaPagamento = pedido.getFormaPagamento();

        if (!restaurante.isAceitaFormaDePagamento(formaPagamento)) {
            var msg = "O restaurante de código %s não aceita a forma pagamento de código %s";
            throw new NegocioException(msg, restaurante.getId(), formaPagamento.getId());
        }
    }
}

package com.danielarrais.algafood.infraestructure.service;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;
import com.danielarrais.algafood.domain.service.ConsultaVendaService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.*;

import static com.danielarrais.algafood.domain.model.StatusPedido.CONFIRMADO;
import static com.danielarrais.algafood.domain.model.StatusPedido.ENTREGUE;

@Repository
public class ConsultaVendaServiceImpl implements ConsultaVendaService {
    private final EntityManager entityManager;

    public ConsultaVendaServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var functionConvertDataCriacao = builder.function("timezone", Date.class, builder.literal("-03:00"), root.get("dataCriacao"));
        var functionDateDataCriacao = builder.function("to_char", String.class, functionConvertDataCriacao, builder.literal("yyyy-MM-dd"));

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        var predicates = new ArrayList<Predicate>();

        if (Objects.nonNull(filter.getDataCriacaoInicio())){
            var predicate = builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio());
            predicates.add(predicate);
        }

        if (Objects.nonNull(filter.getDataCriacaoFim())){
            var predicate = builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim());
            predicates.add(predicate);
        }

        if (Objects.nonNull(filter.getRestauranteId())){
            var predicate = builder.equal(root.get("restaurante"), filter.getRestauranteId());
            predicates.add(predicate);
        }

        predicates.add(root.get("status").in(Arrays.asList(CONFIRMADO, ENTREGUE)));

        query.where(predicates.toArray(new Predicate[0]));
        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        return entityManager.createQuery(query).getResultList();
    }
}

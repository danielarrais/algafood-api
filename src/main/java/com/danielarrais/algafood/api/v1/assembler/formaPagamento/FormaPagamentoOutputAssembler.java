package com.danielarrais.algafood.api.v1.assembler.formaPagamento;

import com.danielarrais.algafood.api.v1.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.v1.util.LinkBuilder;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;

@Component
public class FormaPagamentoOutputAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoOutput> {

    public FormaPagamentoOutputAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoOutput.class);
    }

    @Override
    public FormaPagamentoOutput toModel(FormaPagamento formaPagamento) {
        var formaPagamentoDTO = ModelMapperUtils.mapper(formaPagamento, FormaPagamentoOutput.class);

        formaPagamentoDTO.add(linkFormasPagamento());
        formaPagamentoDTO.add(linkBuscarFormaPagamento(formaPagamento.getId()));

        return formaPagamentoDTO;
    }

    @Override
    public CollectionModel<FormaPagamentoOutput> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities).add(linkFormasPagamento().withSelfRel());
    }

    public FormaPagamentoOutput toModel(Long idRestaurante, FormaPagamento formaPagamento) {
        var formaPagamentoDTO = toModel(formaPagamento);

        return formaPagamentoDTO.add(linkDesassociarFormaPagamentoRestaurante(idRestaurante, formaPagamento.getId()));
    }

    public CollectionModel<FormaPagamentoOutput> toCollectionModel(Long idRestaurante, Iterable<? extends FormaPagamento> entities) {
        var models = new ArrayList<FormaPagamentoOutput>();

        entities.forEach(formaPagamento -> {
            models.add(toModel(idRestaurante, formaPagamento));
        });

        return CollectionModel.of(models)
                .add(LinkBuilder.linkFormasPagamentoRestaurante(idRestaurante))
                .add(linkAssociarFormaPagamentoRestaurante(idRestaurante));
    }
}
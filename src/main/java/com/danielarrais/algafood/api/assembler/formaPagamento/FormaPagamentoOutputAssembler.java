package com.danielarrais.algafood.api.assembler.formaPagamento;

import com.danielarrais.algafood.api.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.linkBuscarFormaPagamento;
import static com.danielarrais.algafood.core.hateoas.LinkBuilder.linkFormasPagamento;

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
}
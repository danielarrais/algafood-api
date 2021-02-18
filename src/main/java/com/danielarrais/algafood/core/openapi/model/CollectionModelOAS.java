package com.danielarrais.algafood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import org.springframework.hateoas.CollectionModel;

@ApiModel("Lista")
public class CollectionModelOAS<T> extends CollectionModel<T> {

}

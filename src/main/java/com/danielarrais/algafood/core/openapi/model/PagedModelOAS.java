package com.danielarrais.algafood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import org.springframework.hateoas.PagedModel;

@ApiModel("Pagina")
public class PagedModelOAS<T> extends PagedModel<T> {

}

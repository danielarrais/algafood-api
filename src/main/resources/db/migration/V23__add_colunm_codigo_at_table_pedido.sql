alter table pedido add codigo varchar(36) not null;

create unique index pedido_codigo_uindex on pedido (codigo);
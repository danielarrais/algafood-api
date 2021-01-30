create table item_pedido
(
    id             serial primary key,
    quantidade     integer default 0 not null,
    preco_unitario numeric(10, 2)           not null,
    preco_total    numeric(10, 2)           not null,
    observacao     varchar(255),
    data_cadastro    timestamp    not null,
    data_atualizacao timestamp    not null,
    produto_id     integer           not null
        constraint item_pedido_produto_id_fk references produto,
    pedido_id      integer           not null
        constraint item_pedido_pedido_id_fk references pedido (id)
);
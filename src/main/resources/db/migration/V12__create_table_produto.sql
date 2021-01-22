create table produto
(
    id               serial primary key,
    descricao        varchar(255)   not null,
    preco            numeric(10, 2) not null,
    ativo            boolean default true,
    data_cadastro    timestamp      not null,
    data_atualizacao timestamp      not null
)
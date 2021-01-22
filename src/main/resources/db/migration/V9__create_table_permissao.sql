create table permissao
(
    id               serial primary key,
    nome             varchar(255) not null,
    descricao        varchar(255) not null,
    data_cadastro    timestamp    not null,
    data_atualizacao timestamp    not null
)
create table forma_pagamento
(
    id               serial primary key,
    descricao        varchar(255) not null,
    data_cadastro    timestamp    not null,
    data_atualizacao timestamp    not null
)
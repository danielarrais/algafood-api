create table cidade
(
    id               serial primary key,
    nome             varchar(255) not null,
    data_cadastro    timestamp    not null,
    data_atualizacao timestamp    not null
)
create table cozinha
(
    id               serial primary key,
    nome             varchar(255),
    data_cadastro    timestamp not null,
    data_atualizacao timestamp not null
)
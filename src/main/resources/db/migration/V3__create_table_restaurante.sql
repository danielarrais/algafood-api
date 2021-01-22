create table restaurante
(
    id               serial primary key,
    nome             varchar(255),
    taxa_frete       numeric(10, 2),
    ativo            boolean,
    data_cadastro    timestamp not null,
    data_atualizacao timestamp not null
)
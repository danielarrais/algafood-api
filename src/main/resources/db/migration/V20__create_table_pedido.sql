create table pedido
(
    id                   serial primary key,
    status               varchar(50) not null,
    subtotal             numeric(10, 2)     not null,
    taxa_frete           numeric(10, 2)     not null,
    valor_total          numeric(10, 2)     not null,
    data_criacao         timestamp   not null,
    data_confirmacao     timestamp,
    data_entrega         timestamp,
    data_cancelamento    timestamp,
    endereco_cep         varchar(255),
    endereco_logradouro  varchar(255),
    endereco_numero      varchar(255),
    endereco_complemento varchar(255),
    endereco_bairro      varchar(255),
    data_cadastro    timestamp    not null,
    data_atualizacao timestamp    not null,
    endereco_cidade_id   integer
        constraint pedido_cidade_id_fk references cidade,
    usuario_id           integer     not null
        constraint pedido_usuario_id_fk references usuario,
    restaurante_id       integer     not null
        constraint pedido_restaurante_id_fk references restaurante,
    forma_pagamento_id   integer     not null
        constraint pedido_forma_pagamento_id_fk references forma_pagamento
);


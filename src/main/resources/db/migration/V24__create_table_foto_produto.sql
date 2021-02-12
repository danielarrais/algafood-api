create table foto_produto
(
    produto_id   bigint       not null
        constraint foto_produto_pk primary key
        constraint foto_produto_produto_id_fk references produto,
    nome_arquivo varchar(150) not null,
    descricao    varchar(150),
    content_type varchar(80)  not null,
    tamanho      int          not null
);


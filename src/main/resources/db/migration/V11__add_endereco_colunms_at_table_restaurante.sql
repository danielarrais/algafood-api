alter table restaurante
    add endereco_cep varchar(255);

alter table restaurante
    add endereco_logradouro varchar(255);

alter table restaurante
    add endereco_numero varchar(255);

alter table restaurante
    add endereco_complemento varchar(255);

alter table restaurante
    add endereco_bairro varchar(255);

alter table restaurante
    add endereco_cidade_id integer;

alter table restaurante
    add constraint restaurante_cidade_id_fk
        foreign key (endereco_cidade_id) references cidade;


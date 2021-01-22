alter table cidade
    add estado_id integer not null;

alter table cidade
    add constraint cidade_estado_id_fk
        foreign key (estado_id) references estado;
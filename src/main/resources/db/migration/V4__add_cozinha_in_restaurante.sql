alter table restaurante
    add cozinha_id integer;

alter table restaurante
    add constraint restaurante_cozinha_id_fk
        foreign key (cozinha_id) references cozinha;
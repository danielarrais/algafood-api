alter table produto
    add restaurante_id integer;

alter table produto
    add constraint produto_restaurante_id_fk
        foreign key (restaurante_id) references restaurante;
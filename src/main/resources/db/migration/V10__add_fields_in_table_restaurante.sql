alter table restaurante
    add taxa_frete numeric(10, 2);

alter table restaurante
    add ativo boolean;

alter table restaurante
    add data_cadastro timestamp;

alter table restaurante
    add data_atualizacao timestamp;
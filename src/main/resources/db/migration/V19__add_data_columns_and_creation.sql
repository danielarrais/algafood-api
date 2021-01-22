alter table restaurante alter column data_cadastro set not null;
alter table restaurante alter column data_atualizacao set not null;

alter table forma_pagamento add data_cadastro timestamp not null;
alter table forma_pagamento add data_atualizacao timestamp not null;

alter table grupo add data_cadastro timestamp not null;
alter table grupo add data_atualizacao timestamp not null;

alter table grupo_permissao add data_cadastro timestamp not null;
alter table grupo_permissao add data_atualizacao timestamp not null;

alter table usuario add data_cadastro timestamp not null;
alter table usuario add data_atualizacao timestamp not null;

alter table produto add data_cadastro timestamp not null;
alter table produto add data_atualizacao timestamp not null;

alter table cozinha add data_cadastro timestamp not null;
alter table cozinha add data_atualizacao timestamp not null;

alter table estado add data_cadastro timestamp not null;
alter table estado add data_atualizacao timestamp not null;

alter table cidade add data_cadastro timestamp not null;
alter table cidade add data_atualizacao timestamp not null;

alter table permissao add data_cadastro timestamp not null;
alter table permissao add data_atualizacao timestamp not null;
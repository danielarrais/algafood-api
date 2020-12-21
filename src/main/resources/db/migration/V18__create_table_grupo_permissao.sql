create table grupo_permissao
(
    gupo_id integer not null
        constraint grupo_permissao_grupo_id_fk
            references grupo,
    permissao_id integer not null
        constraint grupo_permissao_permissao_id_fk
            references permissao
)
create table usuario_grupo
(
    usuario_id integer not null
        constraint usuario_grupo_usuario_id_fk
            references usuario,
    grupo_id   integer not null
        constraint usuario_grupo_grupo_id_fk
            references grupo
)
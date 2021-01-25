create table usuario_responsavel_restaurante
(
    restaurante_id     integer
        constraint usuario_responsavel_restaurante_restaurante_id_fk
            references restaurante,
    usuario_id int
        constraint usuario_responsavel_restaurante_usuario_id_fk
            references usuario
);
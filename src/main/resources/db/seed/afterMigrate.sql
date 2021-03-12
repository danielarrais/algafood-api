-- Truncate all tables
truncate table usuario_grupo restart identity cascade;
truncate table forma_pagamento_restaurante restart identity cascade;
truncate table usuario_grupo restart identity cascade;
truncate table usuario restart identity cascade;
truncate table grupo restart identity cascade;
truncate table permissao restart identity cascade;
truncate table forma_pagamento restart identity cascade;
truncate table produto restart identity cascade;
truncate table restaurante restart identity cascade;
truncate table cozinha restart identity cascade;
truncate table cidade restart identity cascade;
truncate table estado restart identity cascade;

-- Inserts Estados
INSERT INTO estado (nome, data_cadastro, data_atualizacao)
VALUES ('Maranhão', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Bahia', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Cidades
INSERT INTO cidade (nome, estado_id, data_cadastro, data_atualizacao)
VALUES ('Sambaíba', 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Balsas', 2, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Permissoes
INSERT INTO permissao (nome, descricao, data_cadastro, data_atualizacao)
VALUES ('ADMIN', 'Permite tudo', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('ALUNO', 'Permite emição de certificados', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Grupos
INSERT INTO grupo (nome, data_cadastro, data_atualizacao)
VALUES ('Alunos', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Cordenadores', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Terceirizados', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Reitoria', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Usuários
INSERT INTO usuario (nome, email, senha, data_cadastro, data_atualizacao)
VALUES ('Daniel', 'daniel@email.com', '$2y$12$VB1Nm12cFXxisOCEKFMvB.FX7MhutU8Lw9ynO8ymRjexvg8k0P.TW', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Maria', 'maria@email.com', '$2y$12$VB1Nm12cFXxisOCEKFMvB.FX7MhutU8Lw9ynO8ymRjexvg8k0P.TW', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('João', 'joão@email.com', '$2y$12$VB1Nm12cFXxisOCEKFMvB.FX7MhutU8Lw9ynO8ymRjexvg8k0P.TW', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Joana', 'joana@email.com', '$2y$12$VB1Nm12cFXxisOCEKFMvB.FX7MhutU8Lw9ynO8ymRjexvg8k0P.TW', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Cozinha
INSERT INTO cozinha (nome, data_cadastro, data_atualizacao)
VALUES ('Nordestina', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Francesa', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Italiana', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Islamica', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Restaurantes
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao)
VALUES ('Churrascaria Tradição', 1, 10.00, true, '77060140', 'Rua Mato Grosso e Mathias', 'S/N',
        'Predio mais alto da cidade', 'Aureny 1', 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Jackin Predident', 2, 5.00, true, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade',
        'Aureny 1', 2, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Espetinho do Luiz', 3, 0.00, true, '77060140', 'Rua Mato Grosso e Mathias', 'S/N',
        'Predio mais alto da cidade', 'Aureny 1', 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('K-ane Lanches', 4, 0.00, true, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade',
        'Aureny 1', 2, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Formas de Pagamento
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao)
VALUES ('Dinheiro', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Cheque', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Cartão de débito', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Boleto bancário', (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Cartão de crédito', (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Produtos
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao)
VALUES ('Prato feito', 10.00, true, 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Churrasco', 50.00, true, 2, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Espetinho', 15.00, true, 3, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Cozido', 18.00, true, 4, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Macarrão', 10.00, true, 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Refrigerante 350ML', 3.50, true, 2, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Suco Laranja', 5.00, true, 3, (now() at time zone 'utc'), (now() at time zone 'utc')),
       ('Suco Abacaxi', 8.00, true, 4, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts de Pedidos
INSERT INTO public.pedido (codigo, status, subtotal, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega,
                           data_cancelamento, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento,
                           endereco_bairro, endereco_cidade_id, usuario_id, restaurante_id, forma_pagamento_id,
                           data_cadastro, data_atualizacao)
VALUES (uuid_generate_v4(), 'CRIADO', 10.00, 5.00, 15.00, (now() at time zone 'utc'), null, null, null, '77060140',
        'Rua Mato Grosso e Mathias', '8', null, 'Aureny 15', 1, 1, 1, 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (uuid_generate_v4(), 'ENTREGUE', 10.00, 5.00, 15.00, (now() at time zone 'utc'), (now() at time zone 'utc'),
        (now() at time zone 'utc'), null, '77060140',
        'Rua Mato Grosso e Mathias', '8', null, 'Aureny 15', 2, 2, 4, 3, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (uuid_generate_v4(), 'CANCELADO', 10.00, 5.00, 15.00, (now() at time zone 'utc'), (now() at time zone 'utc'), null,
        (now() at time zone 'utc'), '77060140',
        'Rua Mato Grosso e Mathias', '8', null, 'Aureny 15', 1, 4, 2, 2, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (uuid_generate_v4(), 'CONFIRMADO', 10.00, 5.00, 15.00, (now() at time zone 'utc'), (now() at time zone 'utc'), null, null, '77060140',
        'Rua Mato Grosso e Mathias', '8', null, 'Aureny 15', 2, 4, 2, 4, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts de itens de pedido
INSERT INTO public.item_pedido (quantidade, preco_unitario, preco_total, observacao, produto_id, pedido_id,
                                data_cadastro, data_atualizacao)
VALUES (5, 2.00, 10.00, 'Tirar os ovos', 1, 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 2, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 3, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 4, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 4, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 4, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 1, (now() at time zone 'utc'), (now() at time zone 'utc')),
       (5, 2.00, 10.00, 'Tirar os ovos', 1, 3, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Formas de Pagamento
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id)
VALUES (1, 1), (2, 1), (3, 1), (4, 1), (1, 2), (2, 2), (3, 2), (4, 2), (1, 3), (2, 3), (3, 3), (4, 3);

-- Inserts Grupos de Permissão
INSERT INTO grupo_permissao (grupo_id, permissao_id)
VALUES (1, 1), (2, 1), (3, 1), (4, 1), (1, 2), (2, 2), (3, 2), (4, 2);

-- Inserts Grupos de Usuário
INSERT INTO usuario_grupo (usuario_id, grupo_id)
VALUES (1, 1), (2, 2), (3, 3), (4, 4);
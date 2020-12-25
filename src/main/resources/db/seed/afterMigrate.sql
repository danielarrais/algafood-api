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
INSERT INTO estado (nome) VALUES ('Maranhão'); -- 1
INSERT INTO estado (nome) VALUES ('Bahia'); -- 2

-- Inserts Cidades
INSERT INTO cidade (nome, estado_id) VALUES ('Sambaíba', 1); -- 1
INSERT INTO cidade (nome, estado_id) VALUES ('Balsas', 2); -- 2

-- Inserts Permissoes
INSERT INTO permissao (nome, descricao) VALUES ('ADMIN', 'Permite tudo'); -- 1
INSERT INTO permissao (nome, descricao) VALUES ('ALUNO', 'Permite emição de certificados'); -- 2

-- Inserts Grupos
INSERT INTO grupo (nome) VALUES ('Alunos'); -- 1
INSERT INTO grupo (nome) VALUES ('Cordenadores'); -- 2
INSERT INTO grupo (nome) VALUES ('Terceirizados'); -- 3
INSERT INTO grupo (nome) VALUES ('Reitoria'); -- 4

-- Inserts Usuários
INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('Daniel', 'daniel@email.com', 'Sambaíba', current_timestamp); -- 1
INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('Maria', 'maria@email.com', 'Sambaíba', current_timestamp); -- 2
INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('João', 'joão@email.com', 'Sambaíba', current_timestamp); -- 3
INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('Joana', 'joana@email.com', 'Sambaíba', current_timestamp); -- 4

-- Inserts Cozinha
INSERT INTO cozinha (nome) VALUES ('Nordestina'); -- 1
INSERT INTO cozinha (nome) VALUES ('Francesa'); -- 2
INSERT INTO cozinha (nome) VALUES ('Italiana'); -- 3
INSERT INTO cozinha (nome) VALUES ('Islamica'); -- 4

-- Inserts Restaurantes
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Churrascaria Tradição', 1, 10.00, true, current_timestamp, current_timestamp, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 1);
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Jackin Predident', 2, 5.00, true, current_timestamp, current_timestamp, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 2);
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Espetinho do Luiz', 3, 0.00, true, current_timestamp, current_timestamp, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 1);
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('K-ane Lanches', 4, 0.00, true, current_timestamp, current_timestamp, '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 2);

-- Inserts Formas de Pagamento
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro'); -- 1
INSERT INTO forma_pagamento (descricao) VALUES ('Cheque'); -- 2
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de débito'); -- 3
INSERT INTO forma_pagamento (descricao) VALUES ('Boleto bancário'); -- 4
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de crédito'); -- 5

-- Inserts Produtos
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Prato feito', 10.00, true, 1); -- 1
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Churrasco', 50.00, true, 2); -- 2
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Espetinho', 15.00, true, 3); -- 3
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Cozido', 18.00, true, 4); -- 4
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Macarrão', 10.00, true, 1); -- 5
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Refrigerante 350ML', 3.50, true, 2); -- 6
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Suco Laranja', 5.00, true, 3); -- 7
INSERT INTO public.produto (descricao, preco, ativo, restaurante_id) VALUES ('Suco Abacaxi', 8.00, true, 4); -- 8

-- Inserts Formas de Pagamento
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (2, 1);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (3, 1);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (4, 1);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (3, 2);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (4, 2);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (2, 3);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (3, 3);
INSERT INTO forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) VALUES (4, 3);

-- Inserts Grupos de Permissão
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (3, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (4, 1);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 2);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (3, 2);
INSERT INTO grupo_permissao (grupo_id, permissao_id) VALUES (4, 2);

-- Inserts Grupos de Usuário
INSERT INTO public.usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO public.usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO public.usuario_grupo (usuario_id, grupo_id) VALUES (3, 3);
INSERT INTO public.usuario_grupo (usuario_id, grupo_id) VALUES (4, 4);
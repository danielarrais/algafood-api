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
INSERT INTO estado (nome, data_cadastro, data_atualizacao) VALUES ('Maranhão', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO estado (nome, data_cadastro, data_atualizacao) VALUES ('Bahia', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2

-- Inserts Cidades
INSERT INTO cidade (nome, estado_id, data_cadastro, data_atualizacao) VALUES ('Sambaíba', 1, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO cidade (nome, estado_id, data_cadastro, data_atualizacao) VALUES ('Balsas', 2, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2

-- Inserts Permissoes
INSERT INTO permissao (nome, descricao, data_cadastro, data_atualizacao) VALUES ('ADMIN', 'Permite tudo', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO permissao (nome, descricao, data_cadastro, data_atualizacao) VALUES ('ALUNO', 'Permite emição de certificados', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2

-- Inserts Grupos
INSERT INTO grupo (nome, data_cadastro, data_atualizacao) VALUES ('Alunos', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO grupo (nome, data_cadastro, data_atualizacao) VALUES ('Cordenadores', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2
INSERT INTO grupo (nome, data_cadastro, data_atualizacao) VALUES ('Terceirizados', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 3
INSERT INTO grupo (nome, data_cadastro, data_atualizacao) VALUES ('Reitoria', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 4

-- Inserts Usuários
INSERT INTO usuario (nome, email, senha, data_cadastro, data_atualizacao) VALUES ('Daniel', 'daniel@email.com', 'Sambaíba', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO usuario (nome, email, senha, data_cadastro, data_atualizacao) VALUES ('Maria', 'maria@email.com', 'Sambaíba', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2
INSERT INTO usuario (nome, email, senha, data_cadastro, data_atualizacao) VALUES ('João', 'joão@email.com', 'Sambaíba', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 3
INSERT INTO usuario (nome, email, senha, data_cadastro, data_atualizacao) VALUES ('Joana', 'joana@email.com', 'Sambaíba', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 4

-- Inserts Cozinha
INSERT INTO cozinha (nome, data_cadastro, data_atualizacao) VALUES ('Nordestina', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO cozinha (nome, data_cadastro, data_atualizacao) VALUES ('Francesa', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2
INSERT INTO cozinha (nome, data_cadastro, data_atualizacao) VALUES ('Italiana', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 3
INSERT INTO cozinha (nome, data_cadastro, data_atualizacao) VALUES ('Islamica', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 4

-- Inserts Restaurantes
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES ('Churrascaria Tradição', 1, 10.00, true,  '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 1, (now() at time zone 'utc'), (now() at time zone 'utc'));
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES ('Jackin Predident', 2, 5.00, true,  '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 2, (now() at time zone 'utc'), (now() at time zone 'utc'));
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES ('Espetinho do Luiz', 3, 0.00, true,  '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 1, (now() at time zone 'utc'), (now() at time zone 'utc'));
INSERT INTO restaurante (nome, cozinha_id, taxa_frete, ativo, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES ('K-ane Lanches', 4, 0.00, true,  '77060140', 'Rua Mato Grosso e Mathias', 'S/N', 'Predio mais alto da cidade', 'Aureny 1', 2, (now() at time zone 'utc'), (now() at time zone 'utc'));

-- Inserts Formas de Pagamento
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao) VALUES ('Dinheiro', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao) VALUES ('Cheque', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao) VALUES ('Cartão de débito', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 3
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao) VALUES ('Boleto bancário', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 4
INSERT INTO forma_pagamento (descricao, data_cadastro, data_atualizacao) VALUES ('Cartão de crédito', (now() at time zone 'utc'), (now() at time zone 'utc')); -- 5

-- Inserts Produtos
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Prato feito', 10.00, true, 1, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 1
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Churrasco', 50.00, true, 2, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 2
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Espetinho', 15.00, true, 3, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 3
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Cozido', 18.00, true, 4, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 4
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Macarrão', 10.00, true, 1, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 5
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Refrigerante 350ML', 3.50, true, 2, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 6
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Suco Laranja', 5.00, true, 3, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 7
INSERT INTO produto (descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) VALUES ('Suco Abacaxi', 8.00, true, 4, (now() at time zone 'utc'), (now() at time zone 'utc')); -- 8

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
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (3, 3);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (4, 4);
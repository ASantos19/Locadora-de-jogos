-- ADMINISTRADORES
INSERT INTO administrador_model (id, nome, email, senha, nivel_acesso) VALUES (1, 'Administrador Master', 'admin@locadora.com', '123456', 'MASTER');
INSERT INTO administrador_model (id, nome, email, senha, nivel_acesso) VALUES (2, 'Administrador Assistente', 'assistente@locadora.com', 'senha123', 'ASSISTENTE');

-- JOGADORES
INSERT INTO jogador_model (id, apelido, email, senha, data_nascimento) VALUES (1, 'PlayerOne', 'playerone@example.com', 'senha1', DATE '2002-05-10');
INSERT INTO jogador_model (id, apelido, email, senha, data_nascimento) VALUES (2, 'GamerGirl', 'gamergirl@example.com', 'senha2', DATE '2004-11-23');

-- JOGOS
-- NOTE: não insiro administrador_id porque seu JogoModel não possui esse campo no código que você mostrou.
INSERT INTO jogo_model (id, nome_jogo, genero_jogo, plataforma, preco_compra, preco_aluguel) VALUES (1, 'Elden Ring', 'ACAO', 'PC', 299.90, 29.90);
INSERT INTO jogo_model (id, nome_jogo, genero_jogo, plataforma, preco_compra, preco_aluguel) VALUES (2, 'Resident Evil 4 Remake', 'TERROR', 'PS5', 349.90, 34.90);
INSERT INTO jogo_model (id, nome_jogo, genero_jogo, plataforma, preco_compra, preco_aluguel) VALUES (3, 'Civilization VI', 'ESTRATEGIA', 'PC', 199.90, 19.90);

-- LICENÇAS (SINGLE_TABLE)
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_compra, nota_fiscal, data_ativacao, data_expiracao) VALUES (1, 'COMPRA', 'ATIVA', 1, 1, DATE '2024-10-01', 12345, NULL, NULL);
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_compra, nota_fiscal, data_ativacao, data_expiracao) VALUES (2, 'COMPRA', 'ATIVA', 2, 3, DATE '2024-10-11', 67890, NULL, NULL);
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_compra, nota_fiscal, data_ativacao, data_expiracao) VALUES (3, 'ALUGUEL', 'ATIVA', 1, 2, NULL, NULL, DATE '2024-10-09', DATE '2024-10-16');
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_compra, nota_fiscal, data_ativacao, data_expiracao) VALUES (4, 'ALUGUEL', 'EXPIRADA', 2, 1, NULL, NULL, DATE '2024-09-20', DATE '2024-09-27');


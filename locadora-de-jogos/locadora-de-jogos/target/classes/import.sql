-- ============================================
-- SCRIPT DE INICIALIZAÇÃO DE DADOS
-- Sistema de Locadora de Jogos - MySQL
-- ============================================

-- ============================================
-- JOGADORES
-- ============================================
-- IMPORTANTE: O hash BCrypt abaixo é para a senha "password" (exemplo)
-- Para usar a senha "senha123", gere um novo hash usando BCryptPasswordEncoder
-- ou use o endpoint /auth/register para criar usuários com senha correta
INSERT INTO jogador (id, apelido, email, senha, role, data_nascimento) 
VALUES 
(1, 'Admin', 'admin@locadora.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 'ROLE_ADMIN', '1990-01-15'),
(2, 'PlayerOne', 'playerone@example.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 'ROLE_USER', '2002-05-10'),
(3, 'GamerGirl', 'gamergirl@example.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 'ROLE_USER', '2004-11-23'),
(4, 'ProGamer', 'progamer@example.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', 'ROLE_USER', '1998-07-20');

-- ============================================
-- JOGOS
-- ============================================
INSERT INTO jogo_model (id, nome_jogo, genero_jogo, plataforma, preco_compra, preco_aluguel) 
VALUES 
(1, 'Elden Ring', 'ACAO', 'PC', 299.90, 29.90),
(2, 'Resident Evil 4 Remake', 'TERROR', 'PS5', 349.90, 34.90),
(3, 'Civilization VI', 'ESTRATEGIA', 'PC', 199.90, 19.90),
(4, 'The Legend of Zelda: Breath of the Wild', 'AVENTURA', 'SWITCH', 329.90, 32.90);

-- ============================================
-- LICENÇAS - ALUGUEIS
-- ============================================
-- Tabela: licenca_model (SINGLE_TABLE inheritance)
-- Discriminator: tipo_licenca = 'ALUGUEL'
-- Ajustado para referenciar apenas jogos existentes (1-4)
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_ativacao, data_expiracao) 
VALUES 
(1, 'ALUGUEL', 'ATIVA', 2, 1, '2024-01-15', '2024-02-15'),
(2, 'ALUGUEL', 'ATIVA', 3, 2, '2024-01-20', '2024-02-20'),
(3, 'ALUGUEL', 'EXPIRADA', 2, 3, '2023-12-01', '2024-01-01'),
(4, 'ALUGUEL', 'ATIVA', 4, 4, '2024-01-25', '2024-02-25'),
(5, 'ALUGUEL', 'CANCELADA', 3, 1, '2024-01-10', '2024-02-10');

-- ============================================
-- LICENÇAS - COMPRAS
-- ============================================
-- Discriminator: tipo_licenca = 'COMPRA'
-- Ajustado para referenciar apenas jogos existentes (1-4)
INSERT INTO licenca_model (id, tipo_licenca, estado_lic, jogador_id, jogo_id, data_compra, nota_fiscal) 
VALUES 
(6, 'COMPRA', 'VENDIDA', 2, 3, '2023-11-15', 1001),
(7, 'COMPRA', 'VENDIDA', 4, 4, '2023-12-20', 1002),
(8, 'COMPRA', 'VENDIDA', 3, 2, '2024-01-05', 1003),
(9, 'COMPRA', 'VENDIDA', 2, 1, '2024-01-18', 1004),
(10, 'COMPRA', 'VENDIDA', 4, 3, '2024-01-22', 1005);

-- ============================================
-- NOTAS IMPORTANTES:
-- ============================================
-- CREDENCIAIS DE ACESSO (senha padrão: "password"):
-- - Admin: admin@locadora.com / senha: password
-- - PlayerOne: playerone@example.com / senha: password
-- - GamerGirl: gamergirl@example.com / senha: password
-- - ProGamer: progamer@example.com / senha: password
--
-- NOTA SOBRE SENHAS:
-- O hash BCrypt usado é para a senha "password" (exemplo de desenvolvimento)
-- Para usar "senha123", gere um novo hash ou crie usuários via /auth/register
-- Hash atual: $2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW (senha: "password")
--
-- ESTRUTURA DE DADOS:
-- - 4 jogadores (1 admin + 3 usuários)
-- - 4 jogos de diferentes gêneros e plataformas
-- - 5 aluguéis (3 ativos, 1 expirado, 1 cancelado)
-- - 5 compras (todas com status VENDIDA)
--
-- COMPATIBILIDADE MYSQL:
-- - Datas formatadas como 'YYYY-MM-DD' (padrão MySQL)
-- - Referências de chaves estrangeiras ajustadas para jogos existentes
-- - Sintaxe SQL compatível com MySQL 5.7+ e 8.0+
-- ============================================

<<<<<<< HEAD
#Locadora de Jogos – API REST

Este projeto é um sistema de gerenciamento de jogos digitais que permite ao usuário comprar e alugar jogos, armazenando registros de licenças, datas de ativação, expiração, nota fiscal e vínculo com cada jogador.
A aplicação foi desenvolvida em Spring Boot, versionada em camadas e utilizando MySQL como banco de dados.

#Funcionalidades

✔ Cadastro e listagem de jogos
✔ Aluguel temporário (com registro de data de ativação e expiração)
✔ Compra definitiva com geração de nota fiscal
✔ Consulta de licenças por jogador
✔ Controle de acesso com Spring Security + JWT
✔ Suporte completo para execução local e via Docker

#Tecnologias Utilizadas

Java 21	- Linguagem principal
Spring Boot	 - Camadas Web + Service
Spring Security + JWT - Autenticação e Roles
JPA/Hibernate - ORM e persistência
MySQL - Banco relacional
Docker - Containerização e deploy

#Entidades centrais:

JogoModel - Dados do jogo (preços, plataforma, gênero)
JogadorModel - Usuário do sistema
LicencaModel - Classe base (herança)
CompraModel - Licença permanente
AluguelModel - Licença com expiração
DTOs - Troca de informações de forma segura

#COMO RODAR O PROJETO - Execução Local
Pré-requisitos:

Java 21
Maven
MySQL

1. Criar banco de dados:
CREATE DATABASE locadora;

2. Configurar application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/locadora
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update

3. Rodar o sistema:
mvn spring-boot:run


Acesso → http://localhost:8080

#Execução com Docker

1. Gerar .jar:
mvn clean package -DskipTests

2. Build da imagem:
docker build -t locadora-jogos .

3. Executar container:
docker run -p 8080:8080 locadora-jogos

#Endpoints principais

Método	Rota	Descrição
GET	/jogos	Lista jogos
POST	/alugueis/alugar	Alugar jogo
POST	/compras/comprar	Comprar jogo
GET	/alugueis	Histórico de alugueis
GET	/compras	Compras efetuadas

#Conclusão

Este sistema simula um marketplace de jogos digitais utilizando boas práticas de engenharia de software, incluindo DTOs, Service Layer, Repository e segurança com JWT.
Pode evoluir futuramente com renovação automática de aluguel, sistema de pagamento e frontend integrado.
=======
# Locadora-de-jogos
>>>>>>> 1a4596758135db42badedf403caf5495f2cf256a

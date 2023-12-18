# Desafio Java

# Sistema para gerenciar dados de projetos de uma empresa

Tecnologias utilizadas: 
- Java 8
- Spring Boot  
- JPA
- Hibernate
- PostgreSQL 9.4
- JUnit 5
- Mockito
- JSP 
- Bootstrap

O projeto foi implementado considerando os conceitos MVC, com a criação de classes Controller, Service, Repository, Model e DTO.

O IDE IntelliJ Community foi utilizado para o desenvolvimento, com o plugin SonarLint. Os issues identificados foram tratados.

As configurações de banco de dados podem ser alteradas no arquivo application.properties.
As tabelas são criadas automaticamente pelo código, considerando a estrutura informada na especificação do desafio.

Para executar o projeto utilizar o comando: mvn spring-boot:run

Para executar somente os testes unitários: mvn test
Os testes foram implementados para as classes Service existentes no projeto

Conforme proposto na especificação, a aplicação dispõe de webservices para o cadastro de novas pessoas e também para associá-las aos projetos (membro).

Exemplo cURL para o cadastro de novas pessoas - os valores "name" e "position" devem ser informados - "position" pode ser "EMPLOYEE" ou "MANAGER":
curl --location --request POST 'http://localhost:8080/people' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Mateus",
    "position": "EMPLOYEE"
}'

Exemplo cURL para a associação de pessoas e projetos (membro) - os valores de "project" e "person" devem ser informados, ambos se referem aos ids das entidades - "person" deve ser obrigatoriamente um registro do tipo "EMPLOYEE":
curl --location --request POST 'http://localhost:8080/members' \
--header 'Content-Type: application/json' \
--data-raw '{
    "project": "80",
    "person": "96"
}'

A especificação também relata que os atributos "STATUS" e "RISK" de projetos não devem ser cadastrados diretamente no sistema, então também foi criado um webservice para essa função.

Exemplo cURL para a atualização dos campos "STATUS" e "RISK" dos projetos - caso desejado, pode enviar somente um dos valores para atualização - os valores informados devem respeitar o conjunto de opções fornecido na especificação do desafio - o id do projeto deve ser informado diretamente na URL (no exemplo o projeto 80 foi considerado):
curl --location --request PUT 'http://localhost:8080/projects/80' \
--header 'Content-Type: application/json' \
--data-raw '{
    "status": "PLANNED",
    "risk":"HIGH"
}'



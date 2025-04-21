O back-end da aplicação foi desenvolvido com Spring Boot, oferecendo uma API REST. A aplicação é responsável por gerenciar os dados de países, incluindo operações de criação, listagem, edição e exclusão, com autenticação e autorização de usuários.

Tecnologias e Dependências Utilizadas

Spring Boot: Framework principal para construção da API.

Spring Data JPA: Simplifica a persistência e manipulação de dados com repositórios baseados em interfaces.

Spring Security: Implementa a camada de segurança, protegendo as rotas da API e garantindo que apenas usuários autenticados possam acessar determinadas funcionalidades.

DTOs (Data Transfer Objects): Utilizados para transferir dados de forma mais eficiente e segura entre as camadas da aplicação, evitando exposição direta das entidades.

Banco de Dados: Utilizado banco H2 (ou HSQLDB) em memória para testes rápidos e desenvolvimento local.

OpenAPI / Swagger UI: Integração com Swagger para documentação automática e visualização interativa da API REST, disponível em:

🔗 http://localhost:8080/swagger-ui

Segurança:
A aplicação utiliza autenticação baseada em token JWT, garantindo acesso apenas a usuários válidos. Algumas rotas públicas (como login e registro) são liberadas via configuração do SecurityFilterChain, enquanto demais rotas exigem autenticação.

Arquitetura:

A arquitetura está organizada em camadas, promovendo separação de responsabilidades:

Controller:

Responsável por receber as requisições HTTP.

Service: 

Contém a lógica de negócio da aplicação.

Repository:

Interface com o banco de dados via JPA.

DTOs e Mappers: 

Realizam a transformação de entidades em objetos de transferência


Requisições do sistema
![image](https://github.com/user-attachments/assets/b5a0a07b-3316-496b-b729-3b52c3263879)

Requisição de login

![image](https://github.com/user-attachments/assets/cefb2997-bbfc-4149-bffb-18b468d119fd)

Requisição de listagem

![image](https://github.com/user-attachments/assets/2881f7d8-e361-4579-af95-d40b8062b970)

Requisição de criação

![image](https://github.com/user-attachments/assets/7c3b5717-ff11-44ef-96e7-c7fa8e80cdac)




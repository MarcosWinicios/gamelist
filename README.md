# Game list

# Sobre o projeto

Game List é um projeto backend desenvolvido sob orientação da [DevSuperior](https://devsuperior.com "Site da DevSuperior").

O projeto consiste em uma Lista de games e suas informações. Trata-se de uma backend dividido em camadas, onde são disponibilizados endpoints para consultas e reposicionamento de Jogos na lista. 


### Padrão de camadas escolhido
![Image](https://github.com/MarcosWinicios/dsvendas/blob/main/documentation/camadas.png "Padrão camadas")

## Diagrama de Classes

![Modelo de domínio DSList](https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/dslist-model.png)

# Tecnologias utilizadas
## Back end
- Java
- Lombok
- Spring Boot
- JPA / Hibernate
- Maven
- H2 Database
- Postegresql
## Testes
- Postman
## Implantação em produção
- Railway
- Banco de dados: Postgresql

# Como executar o projeto

Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/MarcosWinicios/gamelist.git

# entrar na pasta do projeto back end
cd gamelist

# executar o projeto
./mvnw spring-boot:run
```
# Como testar o projeto
- Baixe a ![Collection Postman](https://github.com/MarcosWinicios/gamelist/blob/main/gamelist.postman_collection.json) contendo todas as requisições para os endpoints disponíveis atualmente.
- Importe a collection baixada no ![Postman](https://www.postman.com/).
- No postman, configure uma variável de ambiente com o nome ```host``` com o valor ```http://localhost:8080```.
- Com isso será possível realizar testes em ambiente local, executando as requisições.
- Caso não queira baixar o projeto em sua máquina, é possível realizar requisições na API que está diponibilizada em deploy.Para isso apenas é necessário seguir os passos anteriores referentes ao Postman e alterar o valor da variável ```host``` para  ```https://gamelist-production-4369.up.railway.app```.
 
# Autor

Marcos Winicios Pereira Martins

https://www.linkedin.com/in/marcoswp/

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

# Autor

Marcos Winicios Pereira Martins

https://www.linkedin.com/in/marcoswp/

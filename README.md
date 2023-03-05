# Spring project assignment
Java Spring Boot project that serves different translations of 'Hello world' from DB or external API.

## Available endpoints
Text inside {} is optional. On every endpoint regarding translations, optional parameter `language` is set on `en` by default.

| Endpoints | Description |
| :------------- |:-------------|
|`/hello-rest{?language=ISO_CODE}`  | Returns a 'Hello World' string. If optional language parameter is provided, its translation(version) will be returned instead if in postgres DB.    | 
| `/hello{?language=ISO_CODE}`   | Returns a 'Hello World' web page. |
| `/secure/hello{?language=ISO_CODE}`     | Returns a 'Hello World'  string but requires authentication. |
| `/secure/admin`     | Returns an admin page that allows adding new Language-Text pairs in DB. Requires authentication with ADMIN account. |
| `/external/hello{?language=ISO_CODE}`     | Returns a 'Hello World' string, translated with external API. |

## Login information
| Email | Password | Role |
| :------------- |:-------------|:-------------|
| admin@gmail.com | 123 | admin |
| user@gmail.com | 123 | user |

## Initial translation entries
| Translation | ISO 639-1 code |
| :------------- |:-------------|
|Hello world| en | 
|Pozdravljen svet| sl |
|Ahoj svet|sk|
|Hola mundo|es|
|Hallo welt|de|
|Bonjour le monde|fr|
|Witaj swiecie|pl|
|Halo dunia|id|
|Ciao mondo|it|
|Hallo wereld|nl|
|Γεια σας κόσμε|el|
|Привіт, світ|uk|


# Project init with spring initializr
## Link
https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.3&packaging=jar&jvmVersion=17&groupId=com.ct&artifactId=springAssignmentProj&name=springAssignmentProj&description=Java%20spring%20assignment%20project%20for%20job%20interview&packageName=com.ct.springAssignmentProj&dependencies=web,data-jpa,postgresql,security,h2

## Project build info
Project: Maven   <br />
Spring Boot: 3.0.3   <br />
Project  name: springAssignmentProj   <br />
Packaging: Jar <br />
Java: 17  

## Dependencies
Spring Web   <br />
Spring Data JPA  <br />
PostgreSQL Driver  <br />
Spring Security  <br />
H2 database  <br />

## Manually added dependencies
Lombok <br />
AssertJ <br />
Mockito (core, junit-jupiter) <br />
Thymeleaf <br />
Validation <br />
AOP <br />
Okhttp3

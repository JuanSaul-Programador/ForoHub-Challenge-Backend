# Foro Hub - Challenge Back End (Alura)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-green)
![Postgres](https://img.shields.io/badge/PostgreSQL-15+-blue)
![Flyway](https://img.shields.io/badge/Flyway-Enabled-red)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-brightgreen)

Bienvenido al repositorio de **Foro Hub**, una API RESTful desarrollada como parte del Challenge Back End de Alura Latam. Esta aplicación permite la gestión de usuarios, cursos, tópicos y respuestas en un foro de discusión, implementando las mejores prácticas de desarrollo y seguridad.

## 🚀 Características Principales

*   **CRUD Completo**: Gestión de Tópicos, Usuarios, Cursos y Respuestas.
*   **Seguridad Robusta**: Autenticación y Autorización mediante **Spring Security** y **JWT** (JSON Web Tokens).
*   **Base de Datos Relacional**: Implementación con **PostgreSQL**.
*   **Migraciones de Base de Datos**: Gestión de esquema y versionado con **Flyway**.
*   **Documentación Interactiva**: API documentada con **Swagger / OpenAPI** para pruebas fáciles.
*   **Validaciones**: Reglas de negocio y validación de datos de entrada (Bean Validation).
*   **Buenas Prácticas**: Arquitectura en capas, DTOs (Records), principios SOLID.

## 🛠️ Tecnologías Utilizadas

*   **Java 17**
*   **Spring Boot 3.2.3**
    *   Spring Web
    *   Spring Data JPA
    *   Spring Security
    *   Spring Validation
*   **PostgreSQL**: Base de datos.
*   **Flyway**: Control de versiones de base de datos.
*   **Auth0 Java-JWT**: Generación y validación de tokens.
*   **SpringDoc OpenAPI**: Documentación automática (Swagger).
*   **Lombok**: Reducción de código boilerplate.

## ⚙️ Configuración y Ejecución

### Prerrequisitos

1.  Java JDK 17 o superior.
2.  Maven.
3.  PostgreSQL instalado y ejecutándose.

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/foro-hub-api.git
cd foro-hub-api
```

### 2. Configurar Base de Datos

Crea una base de datos vacía en PostgreSQL llamada `foro_hub` (o el nombre que prefieras).

Luego, configura las variables de entorno en tu sistema operativo o edita el archivo `src/main/resources/application.properties` si es para pruebas locales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/foro_hub
spring.datasource.username=TU_USUARIO_POSTGRES
spring.datasource.password=TU_CONTRASEÑA_POSTGRES
```

> **Nota sobre Migraciones:** El proyecto utiliza **Flyway**. Al iniciar la aplicación, Flyway creará automáticamente las tablas (`usuarios`, `cursos`, `topicos`, `respuestas`) y sus relaciones. No es necesario ejecutar scripts SQL manualmente.

### 3. Ejecutar la Aplicación

Puedes ejecutar el proyecto desde tu IDE (IntelliJ IDEA, Eclipse) o usando la terminal:

```bash
./mvnw spring-boot:run
```

## 📖 Documentación y Pruebas (Swagger UI)

Una vez iniciada la aplicación, accede a la documentación interactiva en tu navegador:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### ¿Cómo probar los endpoints protegidos?

La mayoría de los endpoints requieren autenticación. Sigue estos pasos en Swagger:

1.  **Registrar Usuario**: Usa el endpoint `POST /usuarios` para crear un nuevo usuario.
2.  **Iniciar Sesión**: Usa el endpoint `POST /login` con tus credenciales.
3.  **Copiar Token**: Copia el token JWT que recibes en la respuesta (campo `jwTtoken`).
4.  **Autorizar**:
    *   Haz clic en el botón verde **Authorize** en la parte superior derecha de Swagger.
    *   Pega el token en el campo de texto.
    *   Haz clic en **Authorize** y luego en **Close**.
5.  **¡Listo!**: Ahora todas tus peticiones incluirán automáticamente el token y podrás usar endpoints como `GET /topicos`, `POST /cursos`, etc.

## 📂 Estructura del Proyecto

*   `domain`: Entidades, Repositorios y DTOs específicos del negocio.
*   `application`: Servicios y lógica de negocio (Casos de uso).
*   `web`: Controladores (Controllers), DTOs de entrada/salida y manejo de excepciones.
*   `infra`: Configuraciones transversales (Seguridad, Swagger/SpringDoc, Manejo de errores).
    *   `security`: Filtros JWT, configuraciones de Spring Security.
    *   `springdoc`: Configuración de OpenAPI.

## 📝 Autor

Desarrollado por **Juan Saul**.

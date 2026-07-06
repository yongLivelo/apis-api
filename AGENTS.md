# apis-api

Spring Boot 4.0.6 REST API — single-module Maven project with Java 17.

## Quick start

```bash
./mvnw spring-boot:run       # dev server (requires MySQL)
./mvnw test                  # full test suite
./mvnw clean package         # build JAR (target/*.jar)
```

## Architecture

- **Entrypoint**: `ApisApiApplication` (`src/main/java/com/apis/apis_api/ApisApiApplication.java`)
- **Model**: `Applicant` JPA entity — `@Entity`, `@Data` (Lombok)
- **Repo**: `ApplicantRepo extends JpaRepository<Applicant, Long>` — auto-exposed as REST resource
- **Controller**: `ApplicantController` — `@RestController` at `/api/applicants` (GET all, POST, PUT, DELETE by id)
- **API docs**: SpringDoc OpenAPI at `/v3/api-docs` and `/swagger-ui/index.html`

## Database

Requires **MySQL** at `localhost:3306/apis-db` with user `root`/`root`. Configured in `src/main/resources/application.properties`. Hibernate DDL auto-update is enabled — tables are created on startup.

**Gotcha**: The single test (`@SpringBootTest` context load) will **fail** without a running MySQL instance. There is no in-memory test profile.

## Development notes

- Uses `mvnw` (Maven wrapper 3.9.14) — no system Maven required.
- Lombok annotation processing is wired via `maven-compiler-plugin` (not `spring-boot-maven-plugin`).
- Package is `com.apis.apis_api` (underscore — the hyphen was invalid for Java).
- No CI, no lint/format config, no pre-commit hooks.

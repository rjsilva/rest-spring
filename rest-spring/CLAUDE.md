# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
./gradlew build          # Full build with tests
./gradlew test           # Run all tests
./gradlew bootRun        # Start the application (port 8080)
./gradlew clean          # Clean build artifacts
./gradlew jacocoTestReport  # Generate code coverage report
```

To run a single test class:
```bash
./gradlew test --tests "br.com.rjs.rest_spring.services.PersonServiceTest"
./gradlew test --tests "integrationtests.swagger.SwaggerIntegrationTest"
```

Integration tests require Docker (Testcontainers spins up MySQL 8.3.0 automatically).

## Architecture

Layered Spring Boot 3.x REST API (Java 21):

- **controllers/** — REST endpoints; `PersonController` is the primary one (`/person`), with `NewPersonController` at `/person/v2`
- **services/** — Business logic (`PersonService` handles CRUD with HATEOAS assembler)
- **repository/** — `PersonRepository` extends `JpaRepository<Person, Long>`
- **model/** — JPA entities mapped to MySQL (`Person` → `tb_name`)
- **dto/** — Java records for request/response (`PersonRequestDto`, `PersonResponseDto`)
- **mapper/** — MapStruct interface (`PersonMapper`) for entity ↔ DTO conversions
- **assembler/** — `PersonModelAssembler` builds `EntityModel` with HAL links (Spring HATEOAS)
- **config/** — `WebConfig` (CORS + content negotiation), `OpenApiConfig` (Swagger)
- **handler/** — `CustomizeEntityResponseHandler` for global exception handling

## Content Negotiation

The API supports JSON, XML, and YAML via the `format` query parameter:
- `?format=json` → `application/json`
- `?format=xml` → `application/xml`
- `?format=yaml` → `application/x-yaml`

`WebConfig` wires this up via `ContentNegotiationConfigurer`.

## Database

- MySQL (production: `localhost:3306/people`, credentials: `root/root123456`)
- Migrations managed by Flyway (`src/main/resources/db/migration/`)
- `ddl-auto = none` — schema changes must be done via Flyway migration files
- Test database is auto-provisioned by Testcontainers (no local MySQL needed for tests)

## Testing Approach

**Unit tests** (`src/test/java/br/com/rjs/rest_spring/`): Mockito-based, use `@Mock`/`@InjectMocks`, no Spring context.

**Integration tests** (`src/test/java/integrationtests/`): Full Spring Boot context, Testcontainers MySQL, RestAssured for HTTP assertions. All integration tests extend `AbstractIntegrationTest` which manages the container lifecycle. Integration tests run on port `8888`.

## Key Configuration Properties

CORS allowed origins are driven by `cors.originPatterns` in `application.yaml`. The Swagger UI is available at `/swagger-ui/index.html` when the app is running.

## DevContainer

A `.devcontainer/` setup is available with Java 21 + Node.js 18 + Claude Code CLI pre-installed. The `docker-compose.yml` provides a `claude` service for containerized development, mounting `~/.anthropic` for the API key.
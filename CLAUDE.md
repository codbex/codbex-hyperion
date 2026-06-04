# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

Hyperion is a codbex Edition product for **BPMN business-process modeling, management and operation**, built on the [Eclipse Dirigible](https://www.dirigible.io) low-code platform (Flowable as the BPM engine) packaged as a Spring Boot application. The repo itself contains very little business logic — it is primarily an **assembly**: it wires together a curated set of Dirigible component artifacts via Maven dependencies, adds branding, and overrides a few UI pieces. Understanding the architecture means understanding *what is assembled and how it is overridden*, not a large local codebase.

Requires **JDK 21**. Default login is `admin` / `admin`; the app serves on port **80**.

## Build & run

All commands run from the repo root.

```shell
# Fast build (skips tests/checks) — produces application/target/codbex-hyperion-*.jar
mvn -T 1C clean install -P quick-build

# Run the standalone jar (the --add-opens flags are required)
java \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
    --add-opens=java.base/java.nio=ALL-UNNAMED \
    -jar application/target/codbex-hyperion-*.jar
# add -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 to debug

# Docker (after a build): from application/
docker build . --tag ghcr.io/codbex/codbex-hyperion:latest
```

Access after start: app at <http://localhost:80>, Swagger UI at <http://localhost/swagger-ui/index.html>.

### Tests

```shell
mvn clean install -P unit-tests          # unit tests only
mvn clean install -P integration-tests   # integration tests (*IT, Selenium-based UI tests)
mvn clean install -P tests               # everything
```

Run a single test with the standard Surefire/Failsafe selector, e.g.:
```shell
mvn test -P unit-tests -Dtest=HyperionApplicationTest
mvn verify -P integration-tests -Dit.test=HomePageIT
```

### Formatting

```shell
mvn verify -P format   # applies the codbex code formatter
```
Code style is enforced by the parent `com.codbex.platform:codbex-platform-parent` POM. Run this before committing Java changes.

## Module layout

Maven multi-module project (`pom.xml` lists the modules). Parent is `codbex-platform-parent`, which supplies dependency versions, the build profiles above, and the formatter — so this repo's POMs carry almost no `<version>` tags.

- **`application/`** — the deployable Spring Boot app (`codbex-hyperion-application`). Its `pom.xml` is the heart of the product: a long, deliberately-curated list of `org.eclipse.dirigible:dirigible-components-*` dependencies that selects exactly which engines, IDE views/editors/menus/perspectives, security providers, and templates ship in Hyperion. **To add or remove a platform feature, edit the dependency list here** rather than writing code. Note the `<exclusions>` that drop stock Dirigible pieces (e.g. `dirigible-components-ui-view-welcome`, `dirigible-components-ui-menu-help`) so the Hyperion overrides below take their place.
- **`components/`** — local Dirigible component overrides (`ui/view-welcome`, `ui/menu-help`) that replace the excluded stock ones.
- **`branding/`** — Hyperion logo, favicon, and branding `project.json` that override Dirigible defaults.
- **`integration-tests/`** — Selenium UI integration tests; no production code.

The only hand-written Java in `application/` is the thin bootstrap: `HyperionApplication.java` (a `@SpringBootApplication` that `scanBasePackages = "org.eclipse.dirigible"` and excludes the JDBC/JPA auto-configs because Dirigible manages datasources itself) plus `AppLifecycleLoggingListener`.

## Dirigible component model (how overrides work)

A Dirigible component is a normal Maven jar whose real payload is **resources under `src/main/resources/META-INF/dirigible/<guid>/`**, not Java classes. Inside that folder:
- `project.json` declares the component `guid`.
- `*.extension` files register an artifact at a Dirigible **extension point** (e.g. `view-welcome/extensions/welcome.extension` binds a config module to the `platform-views` extension point).
- `.html` / `.js` and `configs/` provide the UI and its configuration.

The platform discovers everything on the classpath at runtime by scanning these extension points. So replacing a stock view = exclude the stock artifact in `application/pom.xml` + depend on a local component (in `components/`) that re-registers the same extension point with the same view id. Use `components/ui/view-welcome` as the reference example when creating a new override.

## Integration tests

Tests live in `integration-tests/src/test/java`, named `*IT`, and extend `HyperionIntegrationTest` (a thin subclass of Dirigible's `UserInterfaceIntegrationTest`). They drive a real browser via the `org.eclipse.dirigible.tests.framework` helpers (`IDE`, `browser.assertElementExistsByTypeAndText`, ...). They start the full application, so they only run under the `integration-tests` / `tests` profiles.

## Spring profiles

Default active profiles are `common,app-default` (see `application.properties`). **Eclipse Dirigible only fully activates when the `common` and `app-default` profiles are present** — when overriding `SPRING_PROFILES_ACTIVE`, keep them, e.g. `SPRING_PROFILES_ACTIVE=common,snowflake,app-default`. Security backends (`basic`, `keycloak`, `cognito`, `snowflake`) are selected by which `dirigible-components-security-*` dependency is active plus the chosen profile.

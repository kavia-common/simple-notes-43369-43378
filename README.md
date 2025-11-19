# simple-notes-43369-43378

## Quick Start / Smoke Test

To build and start the backend cleanly (with all devtools and live-reload disabled, and no unintentional port bindings), use:

```shell
cd notes_backend
./gradlew bootJar --no-daemon --console plain
java -jar build/libs/notesbackend-0.1.0.jar
```

The server will:
- Start on port **3001** (see `src/main/resources/application.properties`).
- Health endpoints:  
  - [http://localhost:3001/health](http://localhost:3001/health)  
  - [http://localhost:3001/api/notes](http://localhost:3001/api/notes)
  - [http://localhost:3001/api/info](http://localhost:3001/api/info)
- OpenAPI/Swagger UI: [http://localhost:3001/swagger-ui.html](http://localhost:3001/swagger-ui.html)
- Devtools, live reload, and hot-reload are fully disabled for CI/parity.
- Sample data only loaded on empty DB for speed and stable CI.
# Zariya

Zariya is an institution-centric complaint monitoring prototype for SIH 2026 problem statement SIH26264. It demonstrates a responsive administrative dashboard, fictional sample records, CSV export, complaint intake, and officer-reviewed overlap suggestions. It is a proposed prototype, not an official AICTE service.

## Current scope

- Frontend: React + Vite, responsive dashboard, complaint search/filter, CSV export and intake form. New cases are persisted in browser local storage for this standalone demonstration.
- Backend source: Spring Boot 3 / Java 21 REST starter with PostgreSQL, Flyway migration, Swagger UI and an explainable overlap scoring utility.
- Local Compose file provisions PostgreSQL. Start the frontend with demo data even when the API is unavailable.

This is an initial implementation, not a production system. JWT login and server-enforced roles, bureau-level record authorization, persisted overlap review workflow, audit log, full analytics APIs, and frontend-to-backend integration remain to be implemented before operational use. The dashboard summary includes illustrative counts and should be treated as demo content; they are not measured outcomes. The small case list is fictional.

## Run the frontend

Requires Node.js 20+ and pnpm 9+.

```sh
pnpm install
pnpm dev
```

Open the local URL printed by Vite (normally http://localhost:5173). Use the **Complaint cases** page to search records, export a CSV, or add a fictional case. Added cases remain in that browser's local storage.

## Start the database and API

Requires Docker, Java 21, and Maven 3.9+.

```sh
docker compose up -d postgres
cd backend
mvn spring-boot:run
```

The API listens on http://localhost:8080. OpenAPI UI is at http://localhost:8080/swagger-ui.html. The prototype API permits requests without authentication; do not expose it to a network or use real complaint data. The local Compose credentials are for development only.

## Environment

Copy `.env.example` as needed for Vite. Backend settings are in `backend/src/main/resources/application.yml` and can be overridden with `PORT`, `DATABASE_URL`, `DATABASE_USER`, and `DATABASE_PASSWORD`.

## Build

```sh
pnpm build
cd backend
mvn test package
```

## Deployment and data

Host the built `dist/` directory on a static host and the API on a Java-compatible service with managed PostgreSQL. Configure secrets in the hosting provider and restrict origins before deployment. Do not commit `.env` files, credentials, private keys, or real complaint records. No official AICTE database connection or email service is configured.

## Repository

The source includes a GitHub Actions workflow for the frontend build and backend Maven package. See `.gitignore` for excluded generated files and local secrets.

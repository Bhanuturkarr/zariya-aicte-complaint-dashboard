# Zariya

Zariya is an institution-centric complaint monitoring prototype for SIH 2026 problem statement SIH26264. It demonstrates a responsive administrative dashboard, fictional sample records, CSV export, complaint intake, and officer-reviewed overlap suggestions. It is a proposed prototype, not an official AICTE service.

## Current scope

- Frontend: React + Vite, public landing website and responsive dashboard, searchable institution directory, bureau workload, escalations, reports, CSV export, case intake, status updates, overlap review and an activity log. Changes persist in browser storage for a standalone demo.
- Backend source: Spring Boot 3 / Java 21 API with PostgreSQL, Flyway migration, Swagger UI, complaint create/list/status endpoints, dashboard summary and explainable overlap candidate scoring. Set `VITE_API_URL` to enable API reads and complaint create/status updates; browser storage remains the fallback.
- Local Compose file provisions PostgreSQL. Start the frontend with demo data even when the API is unavailable.

This is a prototype, not a production system. API authentication and server-enforced roles, bureau-level record authorization, database-persisted overlap review decisions and audit logs, and full analytics endpoints remain to be implemented before operational use. The dashboard overview still contains illustrative aggregate charts and must not be treated as measured outcomes. All sample institutions and complaints are fictional.

## Run the frontend

Requires Node.js 20+ and pnpm 9+.

```sh
pnpm install
pnpm dev
```

Open the local URL printed by Vite (normally http://localhost:5173). Use the dashboard to browse institutions and bureau workload, search and filter cases, change a case status, add a fictional complaint, review overlap suggestions, view audit activity, and export CSV reports. Browser-side changes remain in that browser's local storage.

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

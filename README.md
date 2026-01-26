# Employee Leave & Attendance Management System
Java 17 + Spring Boot + Spring Data JPA/Hibernate + MySQL + React.

## Features
- Employee CRUD
- Leave application and balance validation
- Manager/HR approval workflow
- Attendance recording and date-range queries
- Indexed JPA tables
- React dashboard
- Maven/JUnit and Docker/Jenkins configuration

## Run backend
1. Create/start MySQL with username `root`, password `root`.
2. `mvn clean spring-boot:run`

## Run frontend
`cd frontend && npm install && npm run dev`

Frontend defaults to Vite's development port. Backend runs on `8080`.

## Main endpoints
GET/POST/PUT/DELETE `/api/employees`
GET/POST `/api/leaves`
PATCH `/api/leaves/{id}/approve?approverRole=MANAGER`
PATCH `/api/leaves/{id}/reject?approverRole=HR`
GET/POST `/api/attendance`

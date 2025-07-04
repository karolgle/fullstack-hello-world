# Fullstack Hello World

This project contains a minimal full-stack application with a Java Spring Boot backend and a React frontend.

## Backend

Located in the `backend` directory. It is a Gradle-based Spring Boot project exposing a single endpoint at `/api/hello`.

### Running the backend

```bash
cd backend
./gradlew bootRun
```
The Gradle wrapper script will automatically download the required
`gradle-wrapper.jar` if it is not present.

## Frontend

Located in the `frontend` directory. It is a Vite React application that fetches the hello message from the backend on load.

### Running the frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend expects the backend to be running on `http://localhost:8080`.

## Result

Open the browser at the URL printed by Vite (usually `http://localhost:5173`) and you should see the message returned by the backend.

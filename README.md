# 👤 User Management Backend

This is a simple Spring Boot REST API project connected to a MySQL database. It provides CRUD operations for managing users with fields like `id`, `name`, and `email`.

### 🔧 Tech Stack
- Spring Boot (Java)
- MySQL

### 📦 Features
- Create, Read, Update, Delete users
- RESTful API endpoints
- MySQL database integration
- CORS enabled for frontend (e.g., React)

### 🏃‍♂️ Run the Project
Make sure MySQL is running and update your `application.properties`. Then:

```bash
./mvnw clean install
java -jar target/backend-0.0.1-SNAPSHOT.jar

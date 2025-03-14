# GitHub Repo Searcher

This is a Spring Boot application that allows searching for GitHub repositories and storing the results in a PostgreSQL database. The project follows RESTful principles and is designed for efficient database operations, comprehensive test coverage, and modular design.

---

# Project Overview
- **Language:** Java 17  
- **Framework:** Spring Boot  
- **Database:** PostgreSQL  
- **Testing:** JUnit 5, Mockito  
- **Build Tool:** Maven  

---

# Tech Stack

![Image](https://github.com/user-attachments/assets/fc59226b-7e41-40fd-816e-d56c98fa022e)

## Project Structure
src  
├── main  
│   ├── java  
│   │   └── com.example.githubsearcher  
│   │       ├── controller  
│   │       ├── service  
│   │       ├── repository  
│   │       ├── model  
│   │       └── exception  
│   └── resources  
│       └── application.properties  
├── test  
│   └── java  
│       └── com.example.githubsearcher  
│           ├── service  
│           └── controller  


### **1. Controller**  
Defines the REST endpoints and handles incoming HTTP requests.

### **2. Service**  
Contains the business logic and interacts with the repository layer.

### **3. Repository**  
Uses Spring Data JPA to manage database operations.

### **4. Model**  
Defines the entity classes and database mapping.

### **5. Exception**  
Handles global and custom exceptions using `@ControllerAdvice`.

---

## Setup & Installation
### 1. Prerequisites
- Java 17 installed  
- PostgreSQL installed  
- Maven installed  

### 2. Create a new Repository
Create a new repository **Github_Repo_Searcher** in the github account

### 3. Configure Database
Create a PostgreSQL database and configure the credentials in **src/main/resources/application.properties**:

spring.datasource.url=jdbc:postgresql://localhost:5432/githubdb <br>
spring.datasource.username=postgres <br>
spring.datasource.password=password <br>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect <br>
spring.jpa.hibernate.ddl-auto=update

### 4. Build the Project
```sh
mvn clean install
```

### 5. Run the Project
```sh
mvn spring-boot:run
```
### 6. API Endpoints

**GET /repositories**
Get a list of GitHub repositories.
Request:
```sh
curl -X GET http://localhost:8080/repositories
```
Response:
```json
[
  {
    "id": 1,
    "name": "example-repo",
    "description": "Sample repository",
    "url": "https://github.com/example/example-repo",
    "stars": 50,
    "language": "Java"
  }
]

```

**GET /repositories/{id}**
Get a specific repository by ID.
Request:
```sh
curl -X GET http://localhost:8080/repositories/1

```
Response:
```json
{
  "id": 1,
  "name": "example-repo",
  "description": "Sample repository",
  "url": "https://github.com/example/example-repo",
  "stars": 50,
  "language": "Java"
}

```

**POST /repositories**
Save a new repository to the database.
Request:
```sh
curl -X POST http://localhost:8080/repositories -H "Content-Type: application/json" -d '{
  "name": "example-repo",
  "description": "Sample repository",
  "url": "https://github.com/example/example-repo",
  "stars": 50,
  "language": "Java"
}'

```
Response:
```json
{
  "id": 1,
  "name": "example-repo",
  "description": "Sample repository",
  "url": "https://github.com/example/example-repo",
  "stars": 50,
  "language": "Java"
}

```

**DELETE /repositories/{id}**
Save a new repository to the database.
Request:
```sh
curl -X DELETE http://localhost:8080/repositories/1

```
Response:
```json
{
  "message": "Repository deleted successfully"
}

```

### 7. Testing
```sh
mvn test
```
Test Coverage
Unit Tests – Covers service and repository layers.
Integration Tests – Tests interaction with database and external APIs.

### 8. Error Handling
Error Response:
```json
{
  "timestamp": "2025-03-13T12:34:56",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid repository data",
  "path": "/repositories"
}
```
![Image](https://github.com/user-attachments/assets/f6de7e7f-5708-4f03-91fa-ef0efac4ef7e)

### 9. Testable urls for Testable Postman

![Image](https://github.com/user-attachments/assets/84d7fcd4-1648-469f-8999-4c8526e4d81f)

**1. GET All Repositories by Username**
```sh
GET http://localhost:9090/api/github/repositories?username=kusuma-656
```
**2. GET Filtered Repositories by Language and Stars**
```sh
GET http://localhost:9090/api/github/repositories/filter?language=Java&minStars=5
```
**3. GET Sorted Repositories by Forks**
```sh
GET http://localhost:9090/api/github/repositories/filter?sortBy=forks&order=asc
```
**4.POST Create New Repository**
```sh
POST http://localhost:9090/api/github/repositories
```
**5.DELETE Repository by ID**
```sh
DELETE http://localhost:9090/api/github/repositories/1
```
**6.DELETE Repository by Name**
```sh
DELETE http://localhost:9090/api/github/repositories?name=example-repo
```

### 10. Results

![Image](https://github.com/user-attachments/assets/4501c22b-9d1c-4cd4-a51b-df99d1bfac24)
![Image](https://github.com/user-attachments/assets/1c20366e-253e-46b3-bf5a-9d35b2c10447)
![Image](https://github.com/user-attachments/assets/8880ce1d-e64d-4b6b-b6ad-e11758e33b07)


### 11. Design Patterns Used

Following are the design patterns followed: <br>
✔ Service Layer Pattern – Separates business logic from controllers. <br>
✔ Repository Pattern – Handles database operations. <br>
✔ DTO Pattern – For transferring data between layers. <br>
✔ Singleton Pattern – Ensures single instance of key services. <br>

### 12. Future Improvements

✔ Add authentication using OAuth2 for GitHub API. <br>
✔ Implement caching for frequently accessed data. <br>
✔ Introduce pagination and sorting for large data sets. <br>
✔ Add OpenAPI (Swagger) documentation for better API reference. <br>

### 13. Best Practices Followed
✅ RESTful API structure <br>
✅ Separation of concerns (Controller → Service → Repository)<br>
✅ Dependency Injection using **@Autowired** <br>
✅ Efficient Database Operations using **save()** (upsert)<br>
✅ Robust Error Handling with **@ControllerAdvice**<br>
✅ Use of @Transactional for atomic DB operations<br>
✅ Clean and readable code












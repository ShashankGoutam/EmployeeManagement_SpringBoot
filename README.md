# 🧑‍💼 Employee Management System (Spring Boot)

This is a simple **Employee Management System** built using **Java 17**, **Spring Boot 3**, and **MySQL**, developed as part of my internship under the **Uneecops Trainee Program**.

It supports full **CRUD operations** for Employees and Departments, implements **data validation**, **error handling**, and includes **REST API documentation using Swagger**.

---

## 🚀 Features

- 🔧 REST Endpoints for CRUD operations
- 🧑‍💼 Employee ↔ Department mapping (Many-to-One)
- 🛡️ Error handling with custom exceptions
- ✅ Input validation with `jakarta.validation`
- 🔐 **OAuth2 login support using GitHub**
- 🧪 JUnit 5 test cases using `MockMvc` and Mockito
- 📈 **Test coverage with JaCoCo — 80% on new code**
- 🔍 API documentation using Swagger UI
- 📊 Static code analysis with SonarCloud
- 📬 Postman collection support

---

## 🛠️ Technologies Used

| Technology       | Version        |
|------------------|----------------|
| Java             | 17             |
| Spring Boot      | 3.5.0          |
| Maven            | 3.9+           |
| MySQL            | 8+             |
| Lombok           | Yes            |
| Swagger (springdoc-openapi) | 2.8.9 |
| JUnit + Mockito  | JUnit 5        |
| SonarCloud       | Integrated     |
| JaCoCo           | 0.8.10         |
| Spring Security  | OAuth2 Client  |

---

## 📁 Project Structure

```bash
src/
├── main/
│ ├── java/com/example/demo/
│ │ ├── controller/ # REST Controllers
│ │ ├── model/ # JPA Entities (Employee, Department)
│ │ ├── repository/ # Spring Data JPA Repositories
│ │ ├── exception/ # Custom Exceptions
│ │ ├── config/ # Spring Security and OAuth Config
│ │ └── DemoApplication.java
│ └── resources/
│ ├── application.properties
├── test/
│ └── java/com/example/demo/ # Unit & Integration Tests
```


---

## ⚙️ API Endpoints (Examples)

### 🔵 Employees

| Method | Endpoint             | Description                  |
|--------|----------------------|------------------------------|
| GET    | `/employees`         | Get all employees            |
| GET    | `/employees/{id}`    | Get employee by ID           |
| POST   | `/employees`         | Create a new employee        |
| PUT    | `/employees/{id}`    | Update an employee           |
| DELETE | `/employees/{id}`    | Delete an employee           |

Request body example for creating an employee:
```json
{
  "name": "John",
  "role": "Engineer",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "hireDate": "2025-07-28",
  "jobId": "ENG123",
  "salary": 70000,
  "department": {
    "id": 1
  }
}



### 🟢 Departments

| Method | Endpoint            | Description                   |
| ------ | ------------------- | ----------------------------- |
| GET    | `/departments`      | Get all departments           |
| GET    | `/departments/{id}` | Get department with employees |
| POST   | `/departments`      | Create a new department       |

---

### 🔐 OAuth2 Login (GitHub)

Login via GitHub is supported.

- Navigate to: http://localhost:8080/login

- After login, you'll be redirected to /secure which shows the GitHub username.

- Spring Security handles OAuth2 tokens securely via configuration.

- If testing locally without OAuth is needed, the test profile bypasses OAuth and CSRF safely.

---

### 📬 How to Test with Postman
Start the application using:
```bash
mvn spring-boot:run
```
Use Postman to hit endpoints like:

http://localhost:8080/employees

http://localhost:8080/departments

---

### 📄 Swagger UI
Once running, open:
```bash
http://localhost:8080/swagger-ui.html
```
To view and test all endpoints directly.

---

### 📈 Test Coverage & Quality Gate

- ✅ 80% test coverage on new code

- 🧪 Unit & integration tests for controllers and exceptions

- 📊 Static analysis with SonarCloud

- 🛡️ Security Hotspots reviewed (e.g., CSRF disabling is limited to test profile)

---

### 🔍 SonarCloud Integration
This project includes GitHub Actions for:

- Build with Maven

- Run test cases

- Upload coverage and analysis to [SonarCloud](https://sonarcloud.io/)

Make sure to add your Sonar token and organization in GitHub secrets:

SONAR_TOKEN

SONAR_ORGANIZATION


### 🏁 Getting Started (Local Setup)

# Clone the repository
```bash
git clone https://github.com/ShashankGoutam/EmployeeManagement_SpringBoot.git
cd EmployeeManagement_SpringBoot

# Create the database
mysql -u root -p
> CREATE DATABASE employee_db;

# Run the app
mvn spring-boot:run
```

### 📌 Notes
- ✅ This project is part of my ongoing internship at Uneecops Technologies.

- 🚧 Work in progress: adding more test cases, CI/CD enhancements.

### 🙋‍♂️ Author
Shashank Goutam
[GitHub](https://github.com/ShashankGoutam) | [LinkedIn](https://www.linkedin.com/in/shashank-goutam-735924288)





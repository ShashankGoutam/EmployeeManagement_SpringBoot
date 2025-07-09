# Employee Management CRUD API (Spring Boot + MySQL)

This is a simple **Spring Boot** application implementing a **CRUD (Create, Read, Update, Delete)** REST API to manage employees and their departments. The application connects to a **MySQL** database and can be tested using tools like **Postman** or **cURL**.

---

## 🚀 Features

- Create a new employee with department association
- Retrieve all employees
- Update employee details
- Delete an employee
- Exception handling with custom messages

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Jakarta Validation**
- **Postman / Swagger (optional)**

---

## 📁 Project Structure
```
src/
└── main/
├── java/
│ └── com/example/demo/
│ ├── controller/
│ │ └── EmployeeController.java
│ ├── model/
│ │ ├── Employee.java
│ │ └── Department.java
│ ├── repository/
│ │ ├── EmployeeRepository.java
│ │ └── DepartmentRepository.java
│ └── exception/
│ ├── ResourceNotFoundException.java
│ └── GlobalExceptionHandler.java
└── resources/
└── application.properties
```


---

## ⚙️ Setup Instructions

### 1. Clone the repository
```bash
git clone git@github.com:ShashankGoutam/EmployeeManagement_SpringBoot.git
cd EmployeeManagement_SpringBoot
```

### 2. Set up MySQL Database

- Create a database named employee_db

- Update src/main/resources/application.properties

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3. Run the application
``` bash
./mvnw spring-boot:run
```
---

## 🔄 API Endpoints

| Method | Endpoint          | Description           |
| ------ | ----------------- | --------------------- |
| GET    | `/employees`      | Get all employees     |
| POST   | `/employees`      | Create new employee   |
| PUT    | `/employees/{id}` | Update employee by ID |
| DELETE | `/employees/{id}` | Delete employee by ID |

---

## 🧑‍💻 Author
Shashank Goutam

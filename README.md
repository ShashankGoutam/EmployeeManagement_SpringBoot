# Employee Management CRUD API (Spring Boot + MySQL)

This is a simple Spring Boot project implementing a CRUD (Create, Read, Update, Delete) REST API for managing employees. The application uses MySQL as the database and can be tested using Postman or any REST client.

---

## 🚀 Features

✅ Create a new employee  
✅ Get all employees  
✅ Get employee by ID  
✅ Update employee details  
✅ Delete employee by ID  
✅ Basic validation using `@NotBlank`  
✅ Exception handling for not found records  

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- HikariCP (Connection Pool)

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
```bash
git clone https://github.com/ShashankGoutam/EmployeeManagement_SpringBoot.git
```
---

# Employee Management API

📬 **API Endpoints**

| Method | URL                | Description         |
|--------|--------------------|---------------------|
| POST   | `/employees`       | Create a new employee |
| GET    | `/employees`       | Get all employees     |
| GET    | `/employees/{id}`  | Get employee by ID    |
| PUT    | `/employees/{id}`  | Update employee       |
| DELETE| `/employees/{id}`  | Delete employee       |

---

🧪 **Example API Usage**

### Example POST request body (JSON)

```json
{
  "name": "John Doe",
  "role": "Developer"
}


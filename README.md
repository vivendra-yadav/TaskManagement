Task Management API
This is a RESTful Task Management API built with Spring Boot, providing endpoints to manage tasks, including functionalities to search for tasks based on title, description, status, priority, and due date.

Features
Create, Update, Delete tasks
Search tasks by title, description, status, priority, and due date
Use @RequestParam to handle flexible query parameters
Technologies Used
Java 17+
Spring Boot 3.3.4
Spring Data JPA
MySQL (or any other RDBMS)
Maven
Setup Instructions
Prerequisites
Ensure you have the following installed:

Java 17 or higher
Maven 3.8+
MySQL (or your preferred database)
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/vivendra-yadav/TaskManagement.git
cd TaskManagement-api
Configure the database:

Update the src/main/resources/application.properties or application.yml file to match your MySQL configuration:

properties
Copy code
spring.application.name=demo
spring.datasource.url=jdbc:mysql://localhost:3306/task_management_system
spring.jpa.hibernate.ddl-auto=create
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
Build the project:

Run the following Maven command to build the project:

bash
Copy code
mvn clean install
Run the application:

After building, you can start the application by running:

bash
Copy code
mvn spring-boot:run
Alternatively, you can run the compiled JAR file:

bash
Copy code
java -jar target/task-management-api-0.0.1-SNAPSHOT.jar
Database Initialization
The application will automatically create the necessary tables in the database. If spring.jpa.hibernate.ddl-auto is set to update or create, tables like Task will be auto-generated.

API Endpoints
Task Search Endpoints
Search by Title

http
Copy code
GET http://localhost:8080/api/tasks/searchByTitle?title={title}
Parameters:

title: Title of the task (optional)
Response:

200 OK: List of tasks that match the title
Search by Description

http
Copy code
GET http://localhost:8080/api/tasks/searchByDescription?description={taskDescription}
Parameters:

description: Description of the task (optional)
Response:

200 OK: List of tasks that match the description
Search by Status

http
Copy code
GET http://localhost:8080/api/tasks/searchByStatus?status={Todo|In_Progress|Done}
Parameters:

status: Task status (optional)
Response:

200 OK: List of tasks filtered by status
Search by Priority

http
Copy code
GET http://localhost:8080/api/tasks/searchByPriority?priority={LOW|MEDIUM|HIGH}
Parameters:

priority: Task priority (optional)
Response:

200 OK: List of tasks filtered by priority
Search by Due Date

http
Copy code
GET http://localhost:8080/api/tasks/searchByDueDate?dueDate={ISO_LOCAL_DATE_TIME}
Parameters:

dueDate: Due date of the task in yyyy-MM-dd'T'HH:mm:ss format (optional)
Response:

200 OK: List of tasks filtered by due date
Task Management Endpoints
Create Task

http
Copy code
POST http://localhost:8080/api/tasks
Request Body:

json
Copy code
{
    "title": "Complete Report",
    "description": "Finish the end-of-year report",
    "status": "In_Progress",
    "priority": "HIGH",
    "dueDate": "2024-12-31T23:59:59"
}
Response:

201 Created: Task created successfully
Update Task

http
Copy code
PUT /api/tasks/{taskId}
Request Body:

json
Copy code
{
    "title": "Updated Report Title",
    "description": "Updated task description",
    "status": "Done",
    "priority": "MEDIUM",
    "dueDate": "2024-10-01T23:59:59"
}
Response:

200 OK: Task updated successfully
Delete Task

http
Copy code
DELETE http://localhost:8080//api/tasks/{taskId}
Response:

204 No Content: Task deleted successfully
Models
Task
java
Copy code
@Entity
public class Task extends BaseModel {
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;
}
Enums
java
Copy code
public enum Status {
    Todo, In_Progress, Done
}

public enum Priority {
    LOW, MEDIUM, HIGH
}

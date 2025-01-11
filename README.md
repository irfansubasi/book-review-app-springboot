# Book Review Application

A RESTful API for managing book reviews. This application allows users to create, read, update, and delete books, as well as create reviews and manage user accounts. The application includes validation and exception handling for robust error management.

## Features

- **Book Management**: Create, update, view, and delete books.
- **Review Management**: Add, delete, and retrieve reviews for books.
- **User Management**: Create, update, view, and delete users.
- **Validation**: Input validation for book titles, user emails, and other fields.
- **Error Handling**: Custom error messages and HTTP status codes.

## Technologies Used

- **Spring Boot**: Backend framework for building REST APIs.
- **Spring Data JPA**: ORM for data persistence with PostgreSQL.
- **Validation**: Bean validation using annotations like `@NotNull`, `@Email`, etc.
- **Exception Handling**: Global exception handler and custom error responses.
- **PostgreSQL**: Database for storing user, book, and review information.
- **Spring Boot DevTools**: A set of tools to improve the development experience, such as automatic restarts, live reload, and enhanced debugging.
- **Lombok**: A Java library that helps to reduce boilerplate code like getters, setters, constructors, etc.
- **PostgreSQL JDBC Driver**: Enables connectivity between the Spring Boot application and the PostgreSQL database.


## Setup and Installation

### Database Setup

To set up the database schema, create a PostgreSQL database named `bookreviewdb` and execute the following SQL queries:

### Create `users` Table
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
```
### Create `books` Table
```sql
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT
);
```
### Create `reviews` Table
```sql
CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    book_id INT REFERENCES books(id),
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
After running these queries, your database schema will be ready for use.

### Clone the repository:

```bash
git clone https://github.com/irfansubasi/book-review-app-springboot.git
```
### Navigate to the project directory:
```bash
cd <project-directory>
```
### Set up the database:
- Ensure PostgreSQL is installed and running.
- Create a database and configure it in ```application.properties```:
```properties
spring.application.name=bookreview
spring.datasource.url=jdbc:postgresql://localhost:5432/bookreviewdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```

### Build and run the application:
```bash
./mvnw spring-boot:run
```
The application will be available at ```http://localhost:8080```.

## API Endpoints

### Books

- **GET `/books:`** Get all books.
- **GET `/books/{id}:`** Get a book by ID.
- **GET `/books/{bookId}/average-rating:`** Get a book by ID.
- **POST `/books:`** Create a new book.
- **PUT `/books/{id}:`** Update an existing book by ID.
- **DELETE `/books/{id}:`** Delete a book by ID.


### Reviews

- **GET** `/reviews/book/{bookId}`: Get all reviews for a specific book.
- **POST** `/reviews`: Add a review for a specific book.
- **DELETE** `/reviews/{id}`: Delete a review by ID.


### Users

- **GET** `/users`: Get all users.
- **GET** `/users/{id}`: Get a user by ID.
- **POST** `/users`: Create a new user.
- **PUT** `/users/{id}`: Update an existing user by ID.
- **DELETE** `/users/{id}`: Delete a user by ID.

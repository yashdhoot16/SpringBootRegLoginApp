# SpringBootRegLoginApp

This application implements login and registration functionality using Spring Boot, Hibernate, Spring Data JPA, ORM framework, and Bootstrap. It includes the following features:

Validations:
1.Fields for login and registration are validated to ensure that required fields are not left empty.
2.Authorization is implemented to restrict access based on user roles.

Role-based Access:
1.Users can register as either a User or an Admin.
2.Admin Role: Admins have access to view all existing users' data.
3.User Role: Regular users can view only their own logged-in data.

Session Management:
1.Session functionality is implemented to enable users to log out and terminate their session securely.

Database Setup:
1.Install the MySQL Command Line Client.
2.Create a database with the same name as specified in the application.properties file of the application.
3.The creation of tables and columns is automatically managed by Spring Data JPA.

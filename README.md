# SpringBootRegLoginApp

<h5>Technologies: Java, Spring Boot, Spring Data JPA, MySQL, Thymeleaf, ORM, Bootstrap, et</h5>

This application implements login and registration functionality using Spring Boot, Hibernate, Spring Data JPA, ORM framework, and Bootstrap. It includes the following features:

<b>Validations:</b><br>
<ul>
<li>Fields for login and registration are validated to ensure that required fields are not left empty.</li><br>
<li>Authorization is implemented to restrict access based on user roles.</li>
</ul>

<b>Role-based Access:</b><br>
1.Users can register as either a User or an Admin.<br>
2.Admin Role: Admins have access to view all existing users' data.<br>
3.User Role: Regular users can view only their own logged-in data.

<b>Session Management:</b><br>
1.Session functionality is implemented to enable users to log out and terminate their session securely.

<b>Database Setup:</b><br>
1.Install the MySQL Command Line Client.<br>
2.Create a database with the same name as specified in the application.properties file of the application.<br>
3.The creation of tables and columns is automatically managed by Spring Data JPA.

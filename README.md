# SpringBootRegLoginApp

<h5>Technologies: Java, Spring Boot, Spring Data JPA, MySQL, Thymeleaf, ORM, Bootstrap, et</h5>

This application implements login and registration functionality using Spring Boot, Hibernate, Spring Data JPA, ORM framework, and Bootstrap. It includes the following features:

<b>Validations:</b>
<ul>
<li>Fields for login and registration are validated to ensure that required fields are not left empty.</li>
<li>Authorization is implemented to restrict access based on user roles.</li>
</ul>

<b>Role-based Access:</b>
<ul>
<li>Users can register as either a User or an Admin.</li>
<li>Admin Role: Admins have access to view all existing users' data.</li>
<li>User Role: Regular users can view only their own logged-in data.</li>
</ul>

<b>Session Management:</b>
<ul>
<li>Session functionality is implemented to enable users to log out and terminate their session securely.</li>
</ul>

<b>Database Setup:</b>
<ul>
<li>Install the MySQL Command Line Client.</li>
<li>Create a database with the same name as specified in the application.properties file of the application.</li>
<li>The creation of tables and columns is automatically managed by Spring Data JPA.</li>
</ul>

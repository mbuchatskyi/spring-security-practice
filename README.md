## Instructions:

To run this Spring application you need to connect to it a MySQL (or another) database.

You must define the connection attributes in the application.properties file.
You can find it here - src/main/resources/application.properties.

Here is an example:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/users_db
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true
```

## How to run:

1. Start the application in your IDE as Java application. 
2. Go to any browser and enter localhost URL. For example: localhost:8080.

Note that you also can change the server port in the application.properties file: 
```
server.port=8081
```
# Twitter-clone


### Development
- Frontend: Angular 5, Angular Material
- Backend: Spring Boot, Java 8, Hibernate
- Database: MySQL

![Image of Home](https://raw.githubusercontent.com/bizzhou/twitter-clone/master/home.png)

![Image of Home](https://raw.githubusercontent.com/bizzhou/twitter-clone/master/login.png)

### How to run the project
- I have already pre-compiled the javascript files, they are located in **twitter-backend/src/main/java/com.clone.twitter/resources/public**.
- Angular frontend file are located in twitter-frontend/src/app. The **app.component.ts** is the main typescript file that contains logics, and the **app.component.html** is the main html file.

-**Note**: Please make sure your java version is correct

-**Note**: Create a database named "twitter", modify database username and password in **application.properti** in to fit your system.

-**Note**: Database will be recreated everytime you run the Java application. modify *spring.jpa.hibernate.ddl-auto=create-drop* to update if you don't want auto create-drop database.

-**Note**: **import.sql** will run before the system.

```
run the following command in terminal for import into Intellij Idea and run.
$ mvn spring-boot:run

login username: bin@gmail.com
login password: 12345

```



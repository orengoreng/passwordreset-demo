
# passwordreset-demo
Sample [Spring Boot MVC](http://projects.spring.io/spring-boot/) webapp to reset password of a user. Uses [Thymeleaf](https://www.thymeleaf.org/) for site template and memory database [h2](https://www.h2database.com/html/main.html)

## Requirement
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Installation
Use the package manager [mvn](https://maven.apache.org/download.cgi) to install app.
```bash
mvn clean install
```
## Running the application locally

You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

To open the main app page, enter this [link](http://localhost:8080/resetPassword): 

```shell
localhost:8080 or localhost:8080/resetPassword 
```

If you want to access the h2 database memory, click this [link](http://localhost:8080/h2-console) to open the console for database query. See the User table to search for user info.

```shell
localhost:8080/h2-console
```
refer to the `application.properties` for the h2db access.

## Integrating with PasswordReset event

* To track event such as change of password, the app uses [ApplicationEvent](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEvent.html) of Spring to support Publisher to Listener notification to extend integration to other classes.
* If a class wants to interface with PasswordReset event, it has to implement this class listener to capture the event object.
 ```shell
ApplicationListener<ChangePasswordEvent>
``` 
* The app also writes event object into a json file in `app.event.folder` of the application properties to serialize the event for tracking purpose.





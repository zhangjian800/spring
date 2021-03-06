## Rest Example by Using MySQL in Spring Boot via Spring Data JPA and Hibernate

See here for more informations:
https://github.com/zhangjian800/spring/tree/master/spring-boot-mysql-REST

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `curl -v --request GET -k http://localhost:8080/user/`: Get all users from system
    * `curl -v --request POST --header "Content-Type: application/json" --data '{"name":"zhangjain800","email":"zhangjian800@gmail.com"}' http://localhost:8080/user/`: Create user
    * `curl -v --request DELETE -k http://localhost:8080/user/{id}`: delete the user with the passed id.
    * `curl -v --request PATCH --header "Content-Type: application/json" --data '{"name":"zhangjain800","email":"zhangjian800@gmail.com"}' http://localhost:8080/user/{id}`: update user
    * `curl -v --request PATCH --header "Content-Type: application/json" --data '{"name":"zhangjain800","email":"zhangjian800@gmail.com"}' http://localhost:8080/user/{id}`: update user

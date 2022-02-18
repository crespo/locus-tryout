# Tryout for Locus Custom Software
This is a REST API with all the CRUD methods for the tables City and State.

## Running the application

### Requirements
- Java 11

### Installation
First clone the project to your machine using the following command on a terminal:
#### ```git clone https://github.com/crespo/locus-tryout.git```
Then you want to open the root folder:
#### ```cd locus-tryout/```
And finally run the API using the Maven Wrapper:
#### ```./mvnw spring-boot:run```
## Useful testing resources
##### Swagger UI link: http://localhost:9292/swagger-ui/index.html
##### Postman predefined requests: https://www.getpostman.com/collections/3d484a115ac63c1799dd
## Common issues
When executing the Maven Wrapper you can receive the following error:
#### ```Error: JAVA_HOME is not defined correctly.```
Make sure your JAVA_HOME is set one level above the bin/ folder of your Java installation, otherwise, this error will continue to happen.

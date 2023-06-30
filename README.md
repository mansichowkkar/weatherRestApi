# Weather Rest API

Weather rest API provides you weather data depending on date range, sensor name.
It will save data in H2 in memory database as requested.

## Tech Stack

* Zulu JDK 11
* SpringBoot 2.7.13
* Lombok
* Maven 3.6.3

## Running the application

The application is designed to run locally (tomcat server).

## Local configuration

Download/ clone the code from the git repository. Run through run configuration from your ide or through following command from terminal:
jar file can be found under target folder
```
mvn clean install
# go to target directory in terminal and use following command
java -jar weatherRestApi-0.0.1-SNAPSHOT.jar

```

IntelliJ run configuration is available in .run folder. Open the file in run/debug configuration and update "Override
configuration properties" values. Once the config is done, run the application.
Once it is working, ignore the manual way of setting up this.

#### Manual way

Create a new run configuration of type "Spring Boot" in IntelliJ IDEA Ultimate for web and enterprise development.
IntelliJ Community edition does not support Spring Boot run configurations. 


Once the application is launched it is available at:

https://localhost:8028

H2 Database configurations:
all settings have been added in application.properties.
If you want to connect any of your database we can make changes in this file to connect your database

```
"jdbc:sqlserver:
   //[host_name e.g. ech-10-157-129-218.mastercard.int];databaseName="xyz";trustServerCertificate=true"
```

### Debugging HTTP requests

application.properties

```yaml
logging.level.root = DEBUG
```


## Testing

### Unit Test

The unit tests use JUnit with Mockito. They can be run using the command line or maven in IntelliJ.

### Integration Test

Integration tests can be run with Maven surefire plugin on the command line .

The integration tests must run using one of the following configurations:

1. Run tests locally using weatherRestApi running locally first


#### TODO list
1. data.sql is not running which can be resolved and run while application loads
2. Once data.sql loads unit tests can be improved
3. Cover more unit tests and integration tests in future
4. Add more detailed exceptions where required.
5. Not connected to any database like jdbc/sql/oracle which can be done in future
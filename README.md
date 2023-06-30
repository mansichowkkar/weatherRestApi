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
# integration tests may fail since application is not running
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

https://localhost:8080

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

#### API lists
1. POST: http://localhost:8080/weather/temperature ( provide body as mentioned below
```JSON body
{
  "date": "2023-06-26",
  "temperature": 20,
  "humidity": 15,
  "sensor": "sensor1",
  "windVelocity": 40
}
```
2. GET : http://localhost:8080/weather/temperature/2023-06-26/2023-06-28 (replace dates as per your requirement)
3. GET: http://localhost:8080/weather/temperature/2023-06-22/2023-06-28/sensor/sensor1/stats
4. GET: http://localhost:8080/weather/temperature ( when no date and parameters provided it will search for todays date and sensor1 data)


#### TODO list
1. data.sql is not running which can be resolved and run while application loads
2. only Locale Date is supported but in future it will be replaced by Date class os we can even filter data with date and time
3. Once data.sql loads unit tests can be improved
4. Cover more unit tests and integration tests in future
5. Add more detailed exceptions where required.
6. Not connected to any database like jdbc/sql/oracle which can be done in future
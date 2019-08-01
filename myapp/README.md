
### <u>About Application:</u>

This sample application uses: <br/>
Spring Boot as Backend  <br/>
H2 as in-memory database.

The application can be started with the following command:

`mvn spring-boot:run`

This will start embedded tomcat server on port 9090 and the application will be up and running.
<br/>
<br/>
<br/>

### <u>Endpoints:</u>
1. /student/all - Produces a List of all the students present in database.
2. /student/add - Registers a new student via a POST request
3. /student/{id} - Provides a student with the specified {id}
<br/>
<br/>
<br/>

##### <u>Important Points:</u>
1. All errors are redirected to standard error response page: /error
2. The actuator is there to provide simple health of endpoints and various default parameters for the application.
3. With each fresh run the database will be cleared and a default list of students is loaded in memory through a command line runner for tests.

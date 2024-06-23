# dependencies-visualiser
It's an application to visualisation of dependencies in project.

## start application
To start application use `git copy` and download project. Then run application from commnand line as `mvn spring-boot:run`, or './mvnw spring-boot:run' or just in your favourite IDE.

## api
After starting application, you will have access to an api.
Default port is 8080, so localhost:8080 is a server side url.

### endpoints:
/api/dependencies - get pom dependencies in json

/api/dependencies/csv - get pom dependencies in csv file

/api/dependencies/pdf - get pom dependencies in pdf file

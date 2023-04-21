A simple Java Spring application build alongside "Java Microservices with Spring Cloud: Developing Services" Pluralsight course (paywalled). Setup of the client:

- Application configuration data hosted via [Github repo](https://github.com/tomaskul/spring-cloudconfig-course-tolls)
- Cloud Configuration server ([repo](https://github.com/tomaskul/spring-cloudconfig-server-course-tolls)) pulling data from the data repo
- This client:
  - Sourcing configuration data from the cloud configuration server (and decrypting it, to ensure some security in transit)
  - Performs authentication via Github+OAuth2 to permit access to non-root pages

# Running the app
1. Run Configuration server: `mvn spring-boot:run`
2. Run client (this) app: `mvn spring-boot:run`
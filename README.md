# Basic Spring Boot Server configured as GRPC Client

### Reference Documentation
For further reference, please consider the following sections:
* [Spring Boot Documentation](https://spring.io/projects/spring-boot)
* [gRPC Basic Reference Guide](https://grpc.io/docs/tutorials/basic/java/)
* [gRPC Java Implementation](https://github.com/grpc/grpc-java)

### How to Run
* Pull down repository.
* Run command "mvn compile".
* Allow your IDE to index new files.
* Check service class to ensure imports have resolved.
* Run from main method in GrpcClientApplication class.
* In your browser, navigate to localhost:7070/ping to ensure webserver is up and running.
* In your browser, navigate to localhost:7070/greeting/ and add a name to test.
    * i.e. "http://localhost:7070/greeting/Jake"
* If server is up and running succesfully, should return "Hi there, {name}!".

### Notes
* Current POM build cycle involves the creation of docker images from the build artifacts.
* Running a maven install will also attempt to create a docker image, which will only be possible if a local docker daemon is present.


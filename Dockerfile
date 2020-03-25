FROM openjdk:9

EXPOSE 7070
EXPOSE 9191

COPY target/grpc-client.jar /app/client.jar
COPY target/classes/certs /app/certs
COPY target/classes/container.properties /app/application.properties

CMD ["java", "-jar", "-Dgrpc.server.port=9191", "-Dspring.config.location=/app/application.properties", "/app/client.jar"]
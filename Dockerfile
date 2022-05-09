FROM openjdk:latest
EXPOSE 8080
ADD target/tdk-boilerplate-java.jar tdk-boilerplate-java.jar
ENTRYPOINT ["java", "-jar", "tdk-boilerplate-java.jar"]

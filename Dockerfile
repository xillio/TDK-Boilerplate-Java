FROM openjdk:latest
EXPOSE 8080
ADD target/tdk-java.jar tdk-java.jar
ENTRYPOINT ["java", "-jar", "tdk-java.jar"]

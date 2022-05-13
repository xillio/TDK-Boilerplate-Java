FROM openjdk:latest
EXPOSE 8080
COPY src/main/resources/contents /contents
ADD target/tdk-java.jar tdk-java.jar
ENTRYPOINT ["java", "-jar", "tdk-java.jar"]

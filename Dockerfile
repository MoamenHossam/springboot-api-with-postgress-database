FROM openjdk:17-jdk-slim
EXPOSE 3000
ADD target/spring-boot-application.jar .
ENTRYPOINT ["java","-jar","/spring-boot-application.jar"]

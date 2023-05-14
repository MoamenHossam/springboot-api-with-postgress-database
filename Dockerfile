FROM openjdk:11
EXPOSE 3000
ADD target/spring-boot-application.jar .
ENTRYPOINT ["java","-jar","/spring-boot-application.jar"]

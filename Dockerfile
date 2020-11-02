FROM openjdk:8
ADD target/ttunes-spring-boot-assignment.jar ttunes-spring-boot-assignment.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "ttunes-spring-boot-assignment.jar"]
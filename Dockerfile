FROM openjdk:8-jdk-alpine
COPY target/*.jar store.jar
ENTRYPOINT ["java","-jar","/store.jar"]
#Need to configure variables for database
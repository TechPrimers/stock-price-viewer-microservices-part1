FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/*.jar db-service.jar
EXPOSE 8300

ENTRYPOINT ["java","-jar","/db-service.jar"]
FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/*.jar stock-service.jar
EXPOSE 8400

ENTRYPOINT ["java","-jar","/stock-service.jar"]
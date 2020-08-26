FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/*.jar eureka-server.jar

ENTRYPOINT ["java","-jar","/eureka-server.jar"]
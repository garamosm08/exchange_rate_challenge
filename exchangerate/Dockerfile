FROM openjdk:11-jre-slim-buster
MAINTAINER gustavoramosm
EXPOSE 8080
ARG JAR_FILE=target/exchangerate-1.0.0.jar
ADD ${JAR_FILE} exchangerate.jar
ENTRYPOINT ["java","-jar","/exchangerate.jar"]
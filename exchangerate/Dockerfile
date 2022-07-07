FROM maven:3.6.3 AS maven
LABEL MAINTAINER="gustavoramosm"

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean package -DskipTests=true

FROM openjdk:11-jre-slim-buster
EXPOSE 8080
ARG JAR_FILE=exchangerate-1.0.0.jar
WORKDIR /opt/app
#ADD ${JAR_FILE} exchangerate.jar
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","/exchangerate.jar"]
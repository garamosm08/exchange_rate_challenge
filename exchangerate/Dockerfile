FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package -DskipTests=true

FROM openjdk:11-jre-slim-buster
EXPOSE 8080
ARG JAR_FILE=exchangerate-1.0.0.jar
WORKDIR /opt/app
#ADD ${JAR_FILE} exchangerate.jar
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/exchangerate.jar
ENTRYPOINT ["java","-jar","exchangerate.jar"]
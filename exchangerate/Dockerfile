FROM maven:3.6.3 AS maven

ENV M_HOME=/usr/src/app
WORKDIR $M_HOME

ADD pom.xml $M_HOME

RUN mvn verify --fail-never

COPY . $M_HOME

RUN mvn package -DskipTests=true

FROM openjdk:11-jre-slim-buster

ENV J_HOME=/opt/app
ARG JAR_FILE=exchangerate-1.0.0.jar

EXPOSE 8080
WORKDIR $J_HOME

COPY --from=maven /usr/src/app/target/${JAR_FILE} $J_HOME/exchangerate.jar

ENTRYPOINT ["java","-jar","exchangerate.jar"]
# Build maven project
FROM maven:3.8.6-openjdk-18-slim AS base
WORKDIR /vizsgaremek
COPY pom.xml .
RUN mvn dependency:go-offline

COPY ./src /vizsgaremek/src
RUN mvn package -DskipTests

FROM openjdk:18-alpine3.15
WORKDIR /vizsgaremek
COPY --from=base vizsgaremek/target/vizsgaremek-csmate88-0.0.1-SNAPSHOT.jar /vizsgaremek/vizsgaremek-csmate88.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/vizsgaremek/vizsgaremek-csmate88.jar"]

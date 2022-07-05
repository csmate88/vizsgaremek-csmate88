# Build maven project
FROM maven:3.8.6-openjdk-18-slim AS base
WORKDIR /vizsgaremek
COPY pom.xml .
RUN mvn dependency:go-offline

COPY ./src /vizsgaremek/src
ENTRYPOINT ["mvn", "test"]

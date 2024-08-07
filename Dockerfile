FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /test-automation-solution

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean verify -DskipTests
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/cadastroveiculo-api-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "cadastroveiculo-api-0.0.1-SNAPSHOT.jar"]
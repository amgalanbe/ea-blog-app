# STAGE ONE - BUILD PACKAGE
FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn package -DskipTests=true

# STAGE TWO - MOUNT JAR
FROM openjdk:17.0.1-jdk-slim
COPY --from=MAVEN_BUILD /target/Blog-Application-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build target/supermarketbnexdocker.jar app.jar
EXPOSE 8080
CMD [ "java","-jar","app.jar"]
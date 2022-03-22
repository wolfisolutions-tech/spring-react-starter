FROM openjdk:17-slim as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./gradlew clean assemble -x test

FROM openjdk:17-alpine
COPY --from=builder /app/source/build/libs/spring-react-starter-*.jar /app/application.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/application.jar"]
EXPOSE 8080

FROM maven
WORKDIR /app-java
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8080
ENTRYPOINT mvn spring-boot:run

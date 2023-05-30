FROM openjdk:17-jdk-slim
LABEL MAINTAINER ="vijay Rathor"
WORKDIR /app
COPY target/transaction-service.jar  /app/transaction-service.jar
ENTRYPOINT ["java","-jar","order-service.jar"]





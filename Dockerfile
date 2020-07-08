FROM java:8-jdk-alpine
COPY ./build/libs/vertx-web-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vertx-web-0.0.1-SNAPSHOT.war"]

FROM openjdk:8-jre-alpine

ENV APP_HOME /usr/app
WORKDIR $APP_HOME

COPY target/timezone.jar .

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar timezone.jar"]

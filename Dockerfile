FROM openjdk:11-jre-slim

ENV TIME_ZONE              Europe/Paris

ARG ARTIFACT

COPY marathon.json /deployment/

COPY target/$ARTIFACT.jar /app/app.jar

WORKDIR /app
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

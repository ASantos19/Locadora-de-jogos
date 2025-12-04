FROM eclipse-temurin:21-jdk
RUN mkdir /app
WORKDIR /app
COPY target/locadora-de-jogos-0.0.1-SNAPSHOT.jar /app/app.jar

CMD [ "java","-jar", "/app/app.jar" ]
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=/serviceLayer/build/libs/serviceLayer-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app
COPY ${JAR_FILE} bellschooll.jar

ENTRYPOINT ["java","-jar","bellschooll.jar"]
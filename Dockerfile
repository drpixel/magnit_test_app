FROM openjdk:8-jdk-alpine
LABEL maintainer="drpixel@mail.ru"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/test_app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} test_app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Duser.country=RU", "-Duser.language=ru", "-jar","/test_app-0.0.1-SNAPSHOT.jar"]
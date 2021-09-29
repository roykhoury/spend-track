FROM openjdk:11
EXPOSE 8000
ARG JAR_FILE=target/*.jar
ENV JAVA_TOOL_OPTIONS -Xdebug -agentlib:jdwp=transport=dt_socket,address=*:8000,server=y,suspend=n
COPY ${JAR_FILE} spend.track-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/spend.track-0.0.1-SNAPSHOT.jar"]

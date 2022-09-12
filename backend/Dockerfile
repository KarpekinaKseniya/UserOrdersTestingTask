FROM gradle:7.5.1-jdk17-alpine
WORKDIR /

COPY . .
COPY /build/libs/*.jar /app.jar

EXPOSE 8081 8081
ENTRYPOINT ["java","-jar","/app.jar"]
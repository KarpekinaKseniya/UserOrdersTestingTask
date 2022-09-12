# UserOrdersTestingTask

For starting application prefer docker-compose, but you also can use gradle.
</br>
Backend side coverage by unit and integration tests.
</br>
For looking tests coverage you can use gradle command(Report is in /build/reports/jacoco/test/html/index.html)

````
gradle jacocoTestReport
````

### Gradle Instruction

For starts project use commands

````
gradle clean
````

````
gradle bootRun
````

### Docker Instruction

For generated gradle build folder

````
gradle clean build jar
````

Build and Run Docker Images

````
docker-compose up
````

Rebuild Docker Images

````
docker-compose build
````

Local:

+ http://localhost:8080/
+ http://localhost:8080/swagger-ui/index.html

Idea for project took from testing task WG Forge Platform Front-End: https://github.com/wgnet/wg_forge_frontend